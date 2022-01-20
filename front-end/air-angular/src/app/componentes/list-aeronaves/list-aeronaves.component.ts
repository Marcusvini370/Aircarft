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
  p: any = 1;
  total: any;

  ngOnInit(): void {
    this.carregarDados();
  }

  private carregarDados(pagina?: any) {
    this.aeronaveService.getAeronaveListPage((pagina || this.p) - 1).subscribe((data) => {
      if (!data.content.length && data.totalElements) {
        this.carregarDados(data.number);
        return;
      }
      this.aeronaves = data.content;
      this.total = data.totalElements;
      this.recuperarEstatisticas();
    });
  }

  private recuperarEstatisticas() {
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
        this.carregarDados();
      });
    }
  }

  consultaModelo() {
    if (this.nome) {
      this.aeronaveService.consultarAeronavePorPage(this.nome, this.p - 1).subscribe((data) => this.aplicarPaginacaoComPesquisa(data));
    } else {
      this.aeronaveService.getAeronaveListPage(this.p - 1).subscribe((data) => this.aplicarPaginacao(data));
    }
  }

  aplicarPaginacao(data: any) {
    this.aeronaves = data.content;
    this.total = data.totalElements;
  }

  aplicarPaginacaoComPesquisa(data: any) {
    this.aplicarPaginacao(data);

    if (!this.total) {
      alert('Nenhum resultado encontrado');
    }
  }

  consultarComPaginaValida(pagina: any) {
    this.p = pagina;
    this.aeronaveService
      .consultarAeronavePorPage(this.nome, this.p = 0)
      .subscribe((data) => this.aplicarPaginacao(data));
  }

  carregarPagina(pagina: any) {
    if (this.nome) {
      this.aeronaveService
        .consultarAeronavePorPage(this.nome, pagina - 1)
        .subscribe((data) => this.aplicarPaginacaoComPesquisa(data));
    } else {
      this.aeronaveService.getAeronaveListPage(pagina - 1).subscribe((data) => this.aplicarPaginacao(data));
    }
  }

  imprimeRelatorioDisponiveis(){
    return this.aeronaveService.PdfRelatorioDisponiveis();
  }

  imprimeRelatorioSemanal(){
    return this.aeronaveService.pdfRelatorioSemanal();
  }

  imprimeRelatorioVendidas(){
    return this.aeronaveService.pdfRelatorioVendidas();
  }

}
