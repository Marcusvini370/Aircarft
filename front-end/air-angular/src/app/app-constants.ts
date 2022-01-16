export class AppConstants {

  public static get baseServidor(): string { return "https://localhost:8080/" }
  public static get baseUrl(): string {return this.baseServidor + "aircraft/aeronaves/"}

}
