import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'texttransform'})
export class TextTransformPipe implements PipeTransform {

transform(value: any) {
    if(value == null || value == "" || value == 'undefined') {
        return '-';
    } else {
        return value;
    }
}
}