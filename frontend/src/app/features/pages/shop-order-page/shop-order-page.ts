import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { ShopOrder } from '../../../shared/model-interface/ShopOrder';
import { ApiService } from '../../../service/api.service';
@Component({
  selector: 'app-shop-order-page',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './shop-order-page.html',
  styleUrl: './shop-order-page.scss'
})
export class ShopOrderPage implements OnInit{

  shopOrder$!:Observable<ShopOrder[]>;

  constructor(private api: ApiService){

  }
  ngOnInit(): void {
    



    
  }

}
