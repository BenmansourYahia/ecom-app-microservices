import { Component, OnInit } from '@angular/core';
import { ChatbotService, ChatMessage } from '../../services/chatbot';

@Component({
    selector: 'app-chatbot',
    standalone: false,
    templateUrl: './chatbot.html',
    styleUrl: './chatbot.css',
})
export class ChatbotComponent implements OnInit {
    messages: ChatMessage[] = [];
    userInput = '';
    isLoading = false;
    isOpen = false;

    constructor(private chatbotService: ChatbotService) { }

    ngOnInit(): void {
        // Welcome message
        this.messages.push({
            role: 'assistant',
            content: 'Bonjour ! Je suis votre assistant e-commerce. Comment puis-je vous aider ?',
            timestamp: new Date()
        });
    }

    toggleChat(): void {
        this.isOpen = !this.isOpen;
    }

    sendMessage(): void {
        if (!this.userInput.trim() || this.isLoading) {
            return;
        }

        const userMessage: ChatMessage = {
            role: 'user',
            content: this.userInput,
            timestamp: new Date()
        };

        this.messages.push(userMessage);
        const query = this.userInput;
        this.userInput = '';
        this.isLoading = true;

        this.chatbotService.sendMessage(query).subscribe({
            next: (response) => {
                const assistantMessage: ChatMessage = {
                    role: 'assistant',
                    content: response,
                    timestamp: new Date()
                };
                this.messages.push(assistantMessage);
                this.isLoading = false;
                this.scrollToBottom();
            },
            error: (err) => {
                const errorMessage: ChatMessage = {
                    role: 'assistant',
                    content: 'Désolé, une erreur est survenue. Veuillez réessayer.',
                    timestamp: new Date()
                };
                this.messages.push(errorMessage);
                this.isLoading = false;
                console.error('Chatbot error:', err);
            }
        });
    }

    onKeyPress(event: KeyboardEvent): void {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault();
            this.sendMessage();
        }
    }

    private scrollToBottom(): void {
        setTimeout(() => {
            const chatMessages = document.querySelector('.chat-messages');
            if (chatMessages) {
                chatMessages.scrollTop = chatMessages.scrollHeight;
            }
        }, 100);
    }
}
