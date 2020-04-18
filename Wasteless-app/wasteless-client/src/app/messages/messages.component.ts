import {Component, OnDestroy, OnInit} from '@angular/core';
import {RxStompService} from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
})
export class MessagesComponent implements OnInit, OnDestroy {
  private topicSubscription: Subscription;

  constructor(private rxStompService: RxStompService) { }

  public receivedMessages: string[] = [];

  ngOnInit() {
    this.topicSubscription = this.rxStompService.watch('/chat').subscribe((message: Message) => {
      this.receivedMessages.push(message.body);
    });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

  onSendMessage() {
    const message = `Message generated at ${ new Date() }`;
    this.rxStompService.publish({destination: '/app/send/message', body: message});
  }
}
