export class AppConstants {

  public static get baseServidor(): string { return "http://marcus-aircraft.herokuapp.com/" }
  public static get baseUrl(): string {return this.baseServidor + "aircraft/aeronaves/"}

}
