export class AppConstants {

  public static get baseServidor(): string { return "https://marcus-aircraft.herokuapp.com/" }

  public static get baseUrl(): string {return this.baseServidor + "aircraft/aeronaves/"}

}
