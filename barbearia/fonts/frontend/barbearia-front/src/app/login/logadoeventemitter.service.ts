import { Injectable } from '@angular/core';
import { EventEmitter} from '@angular/core';

@Injectable()
export class LogadoEventEmitterService
{
    emitter : EventEmitter<void> = new EventEmitter<void>();
}
