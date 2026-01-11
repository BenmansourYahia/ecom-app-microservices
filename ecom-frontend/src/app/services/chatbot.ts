import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ChatMessage {
  role: 'user' | 'assistant';
  content: string;
  timestamp: Date;
}

export interface ChatResponse {
  response: string;
}

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {
  private apiUrl = '/api/chat';

  constructor(private http: HttpClient) { }

  sendMessage(query: string): Observable<string> {
    const params = new HttpParams().set('query', query);
    return this.http.get(this.apiUrl, { params, responseType: 'text' });
  }
}
