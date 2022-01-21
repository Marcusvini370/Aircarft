import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AeronaveService } from 'src/app/service/aeronave.service';

import { AeronaveUtils } from '../../utils/aeronave-utils';


@Component({
  selector: 'app-aeronave',
  templateUrl: './aeronave.component.html',
  styleUrls: ['./aeronave.component.css'],
})
export class AeronaveComponent implements OnInit {

  readonly TAMANHO_LIMITE_DESCRICAO: number = 255;

  idEdicao: number = 0;
  listaMarcas: string[] = AeronaveUtils.marcasAsSelect();

  formulario: FormGroup = new FormGroup({
    nome: new FormControl(null, [Validators.required, Validators.maxLength(30)]),
    marca: new FormControl(null, Validators.required),
    ano: new FormControl(null, [Validators.required, Validators.min(1920), Validators.max(2022)]),
    vendido: new FormControl(null, Validators.required),
    descricao: new FormControl(null, Validators.maxLength(this.TAMANHO_LIMITE_DESCRICAO)),
  });

  constructor(private routerActive: ActivatedRoute, private aeronaveService: AeronaveService) { }

  ngOnInit(): void {
    this.inicializarFormularioEdicao();
  }

  salvarAeronave() {
    AeronaveUtils.validarTodosForms(this.formulario);

    if (this.formulario.valid) {
      if (this.idEdicao) {
        /* Atuzalizando ou editando se o usuário existir*/
        this.aeronaveService.updateAeronave(this.idEdicao, this.formulario.value).subscribe((data) => {
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

  }

  novo() {
    this.formulario.reset();
  }

  private inicializarFormularioEdicao() {
    const id = this.routerActive.snapshot.paramMap.get('id');

    if (id) {
      this.idEdicao = Number(id);

      this.aeronaveService.getAeronave(this.idEdicao).subscribe((data) => {
        this.formulario.patchValue(data);
      });
    }
  }

}
