import { EnumMarca } from "../model/aeronave";

    export class utils {
      public static marcasAsSelect(): string[] {
        const listaMarcas: string[] = [];

        for (const item of Object.values(EnumMarca)) {
          listaMarcas.push(item.toString());
        }

        return listaMarcas;
      }
    }
