import { Component, OnInit } from '@angular/core';
import { Aeronave } from './model/aeronave';
import { AeronaveService } from './service/aeronave.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'air-angular';


  aeronaves: Array<Aeronave> = [
      {id:0, nome: "", marca: "", ano: 0, vendido: false, descricao: ""},
  ];

  constructor(private aeronaveService: AeronaveService){}

  ngOnInit(): void {
    this.aeronaveService.getAeronaveList().subscribe((data) =>{
      this.aeronaves = data;
    });
  }

  deleteAeronave(id: Number){
    this.aeronaveService.deleteAeronave(id).subscribe( data => {

      this.aeronaveService.getAeronaveList().subscribe((data) =>{
        this.aeronaves = data;
      });

    });
  }


}
