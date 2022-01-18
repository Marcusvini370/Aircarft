import { utils } from './../../AeronaveUtils/Utils';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Aeronave } from 'src/app/model/aeronave';
import { AeronaveService } from 'src/app/service/aeronave.service';

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

  aeronave = {} as Aeronave;
  listaMarcas: string[] = utils.marcasAsSelect();
  alertShow = false;
  alertMessage: string = '';

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

  validacaoForm() {
    if (this.aeronave.ano <= 1960 || this.aeronave.ano > 2022) {
      this.alertMessage = `Ano inválido voce digitou ${this.aeronave.ano}, digite um ano no intervalo de 1960 a 2022 `;
      this.alertShow = true;
      return true;
    }
    this.alertShow = false;
    return false;
  }

  salvarAeronave() {
    if (this.validacaoForm()) {
      return;
    }

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
