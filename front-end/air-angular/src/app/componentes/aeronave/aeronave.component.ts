import { utils } from './../../AeronaveUtils/Utils';

import { Component, OnInit, } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Aeronave } from 'src/app/model/aeronave';
import { AeronaveService } from 'src/app/service/aeronave.service';
import {  FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-aeronave',
  templateUrl: './aeronave.component.html',
  styleUrls: ['./aeronave.component.css'],
})
export class AeronaveComponent implements OnInit{

  aeronave = {} as Aeronave;
  listaMarcas: string[] = utils.marcasAsSelect();
  alertShow = false;
  alertMessage: string = '';

  constructor(private routerActive: ActivatedRoute,private aeronaveService: AeronaveService
  ) {
  }


  ngOnInit(): void {
   let id = this.routerActive.snapshot.paramMap.get('id');

    if (id != null) {
      this.aeronaveService.getAeronave(id).subscribe((data) => {
        this.aeronave.id = data.id;
        this.aeronave.marca = data.marca;
        this.aeronave.nome = data.nome;
        this.aeronave.ano = data.ano;
        this.aeronave.vendido = data.vendido;
        this.aeronave.descricao = data.descricao;
      });
    }
  }

  formulario: FormGroup = new FormGroup({

    nome: new FormControl(null, Validators.required),
    marca: new FormControl(null, Validators.required),
    ano: new FormControl(null, [Validators.required, Validators.min(1920), Validators.max(2022)]),
    vendido: new FormControl(null, Validators.required),
    descricao: new FormControl(null),

  });



  validacaoForm() {
    if (this.aeronave.ano <= 1920 || this.aeronave.ano > 2022) {
      this.alertMessage = `Ano inválido voce digitou ${this.aeronave.ano}, digite um ano no intervalo de 1920 a 2022 `;
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

    if(this.formulario.valid){
    if (this.aeronave.id != null && this.aeronave.id.toString().trim() != null
    ) {
      /* Atuzalizando ou editando se o usuário existir*/
      this.aeronaveService.updateAeronave(this.aeronave.id,this.formulario.value).subscribe((data) => {
        alert('Registro Atualizado');

      });
    } else {
      //salvando
      this.aeronaveService.salvarAeronave(this.formulario.value).subscribe((data) => {
        alert('Aeronave cadastrada com sucesso');
        this.novo();
      });
    }
  }

  if  (this.ano.errors?['min'] || this.ano.errors:['max']) {
    alert("Campo ano está inválido digite um valor entre o intervalo de 1920 a 2022");
 } else if (this.ano.errors?.['required']) {
  alert("Campo ano está inválido digite um valor entre o intervalo de 1920 a 2022");
 }



  }

  novo() {
    this.aeronave = new Aeronave();
    this.formulario.reset();
    this.alertShow = false;
  }


get ano(): FormControl {
  return this.formulario.get('ano') as FormControl;
}


}
