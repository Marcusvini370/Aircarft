import { GrupoTotalDTO } from './../../model/GrupoTotalDTO';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AeronaveService } from 'src/app/service/aeronave.service';
import { Aeronave } from 'src/app/model/aeronave';
import { utils } from 'src/app/AeronaveUtils/utils';

@Component({
  selector: 'app-aeronave',
  templateUrl: './aeronave.component.html',
  styleUrls: ['./aeronave.component.css'],
})
export class AeronaveComponent implements OnInit {
  constructor(
    private routerActive: ActivatedRoute,
    private aeronaveService: AeronaveService
  ) {}

  ngOnInit(): void {
    let id = this.routerActive.snapshot.paramMap.get('id');

    if (id != null) {
      this.aeronaveService.getAeronave(id).subscribe((data) => {
        this.aeronave.marca = data.marca;
        this.aeronave.nome = data.nome;
        this.aeronave.ano = data.ano;
        this.aeronave.vendido = data.vendido;
        this.aeronave.descricao = data.descricao;
      });
    }
  }

  aeronave = {} as Aeronave;
  listaMarcas: string[] = utils.marcasAsSelect();

  salvarAeronave() {
    if (
      this.aeronave.id != null &&
      this.aeronave.id.toString().trim() != null
    ) {
      /* Atuzalizando ou editando se o usuário existir*/
      this.aeronaveService.updateAeronave(this.aeronave).subscribe((data) => {
        alert('Registro Atualizado' + data);
        this.novo();
      });
    } else {
      //salvando
      this.aeronaveService.salvarAeronave(this.aeronave).subscribe((data) => {
        alert('Aeronave cadastrada com sucesso');

        this.novo();
      });
    }
  }

  novo() {
    this.aeronave = new Aeronave();
  }
}
