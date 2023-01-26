
import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import { AdsenseModule } from 'ng2-adsense';


@Component({
	
  selector: 'app-ads-horizontal',
template: ` <div>
        <ins class="adsbygoogle"
           style="display:block;"
            data-ad-client="ca-pub-8593839440328483"
    [attr.data-ad-slot]="data"
            data-ad-format="auto"></ins>
         </div>   
            <br>            
  `,
})
export class AdsHorizontalComponent {//implements AfterViewInit{
	
	@Input() data: any;
	
/*	ngAfterViewInit() {
setTimeout(() => {
 try {
    (adsbygoogle = window.adsbygoogle || []).push({});
  } catch (e) {
    console.error(e);
  }
}, 2000);
} 
*/
}
