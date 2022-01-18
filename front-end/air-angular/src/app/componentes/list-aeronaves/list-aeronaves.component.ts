import { forkJoin } from 'rxjs';
import { GrupoTotalDTO } from './../../model/GrupoTotalDTO';
import { Component, OnInit } from '@angular/core';
import { Aeronave } from 'src/app/model/aeronave';
import { AeronaveService } from 'src/app/service/aeronave.service';

@Component({
  selector: 'app-list-aeronaves',
  templateUrl: './list-aeronaves.component.html',
  styleUrls: ['./list-aeronaves.component.css'],
})
export class ListAeronavesComponent implements OnInit {
  aeronaves: Array<Aeronave> = [
    { id: 0, nome: '', marca: '', ano: 0, vendido: false, descricao: '' },
  ];

  constructor(private aeronaveService: AeronaveService) {}

  grupoMarcas: GrupoTotalDTO[] = [];
  grupoDecadas: GrupoTotalDTO[] = [];
  nome: string = '';
  regSemanal!: Number;
  relNaoVendida!: Number;
  p: any;
  total: any;

  ngOnInit(): void {
    this.aeronaveService.getAeronaveList().subscribe((data) => {
      this.aeronaves = data;
      this.total = data.totalElements;
    });

    forkJoin([
      this.aeronaveService.getAeronaveSemanal(),
      this.aeronaveService.getAeronaveNaoVendida(),
      this.aeronaveService.getAeronaveDecada(),
      this.aeronaveService.getAeronavMarcaQuantidade(),
    ]).subscribe(([semanal, naoVendida, decada, marca]) => {
      this.grupoMarcas = marca;
      this.grupoDecadas = decada;
      this.relNaoVendida = naoVendida.disponiveis;
      this.regSemanal = semanal.semanal;
    });
  }

  deleteAeronave(id: Number, index: any) {
    if (confirm('Deseja mesmo remover?')) {
      this.aeronaveService.deleteAeronave(id).subscribe((data) => {
        this.aeronaves.splice(index, 1);
      });
    }
  }

  consultaModelo() {
    if (this.nome.length > 0) {
      this.aeronaveService.consultarModelo(this.nome).subscribe((data) => {
        this.aeronaves = data;
        this.total = data.totalElements;
        this.p = 1;
        console.log(data);
      });
    } else {
      this.aeronaveService.getAeronaveList().subscribe((data) => {
        this.aeronaves = data;
        this.total = data.totalElements;
        console.log(data);
      });
    }
  }

  carregarPagina(pagina: any) {
    if (this.nome !== '') {
      this.aeronaveService
        .consultarAeronavePorPage(this.nome, pagina - 1)
        .subscribe((data) => {
          this.aeronaves = data.content;
          this.total = data.totalElements;
          console.log(data);
        });
    } else {
      this.aeronaveService.getAeronaveListPage(pagina - 1).subscribe((data) => {
        this.aeronaves = data.content;
        this.total = data.totalElements;
        console.log(data);
      });
    }
  }
}
