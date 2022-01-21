import { EnumMarca } from "../model/aeronave";
import { FormArray, FormControl, FormGroup } from '@angular/forms';

export class AeronaveUtils {

  public static marcasAsSelect(): string[] {
    const listaMarcas: string[] = [];

    for (const item of Object.values(EnumMarca)) {
      listaMarcas.push(item.toString());
    }

    return listaMarcas;
  }

  public static validarTodosForms(formulario: FormGroup | FormArray) {
    Object.keys(formulario.controls).forEach(campo => {
      const formControl = formulario.get(campo);
      if (formControl instanceof FormControl && formControl.enabled) {
        formControl.markAsTouched({ onlySelf: true });
        formControl.markAsDirty({ onlySelf: true });
      } else if (formControl instanceof FormGroup) {
        this.validarTodosForms(formControl);
      } else if (formControl instanceof FormArray) {
        for (const item of formControl.controls) {
          if (item instanceof FormGroup || item instanceof FormArray) {
            this.validarTodosForms(item);
          } else if (item instanceof FormControl && formControl.enabled) {
            item.markAsTouched({ onlySelf: true });
            item.markAsDirty({ onlySelf: true });
          }
        }
      }
    });
  }
}
