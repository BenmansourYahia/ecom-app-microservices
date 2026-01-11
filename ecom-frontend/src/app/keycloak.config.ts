import { APP_INITIALIZER, ApplicationConfig, Provider } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors, HttpInterceptorFn } from '@angular/common/http';
import { KeycloakService } from 'keycloak-angular';
import { inject } from '@angular/core';

// Keycloak initialization function
function initializeKeycloak(keycloak: KeycloakService) {
    return () =>
        keycloak.init({
            config: {
                url: 'http://localhost:8080',
                realm: 'ecom-realm',
                clientId: 'ecom-frontend'
            },
            initOptions: {
                onLoad: 'login-required',
                checkLoginIframe: false
            },
            bearerExcludedUrls: ['/assets']
        });
}

// HTTP Interceptor for adding JWT token
const authInterceptor: HttpInterceptorFn = (req, next) => {
    const keycloak = inject(KeycloakService);
    const token = keycloak.getToken();

    if (token) {
        req = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
    }

    return next(req);
};

// Keycloak providers
export const keycloakProviders: Provider[] = [
    KeycloakService,
    {
        provide: APP_INITIALIZER,
        useFactory: initializeKeycloak,
        multi: true,
        deps: [KeycloakService]
    }
];

// HTTP client with auth interceptor
export function provideHttpClientWithAuth() {
    return provideHttpClient(withInterceptors([authInterceptor]));
}
