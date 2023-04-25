import { formatDate } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from "@angular/forms";

@Component({
  selector: "app-mat-datepicker-wrapper",
  templateUrl: "./mat-datepicker-wrapper.component.html",
  styleUrls: ["./mat-datepicker-wrapper.component.css"],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: MatDatepickerWrapperComponent,
      multi: true
    }
  ]
})
export class MatDatepickerWrapperComponent implements ControlValueAccessor {
  public model: string | undefined;
  public onChange!: Function;
  public onTouched: Function | undefined;

  writeValue(value: string): void {
    // Transform the value to iso date
    this.model = value;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  // modelChange(value: string) {
  //   const dateValue = new Date(value);
  //   const transformedValue = `CUSTOM-DATE-VALUE-${dateValue.getFullYear()}-${dateValue.getMonth() +
  //     1}`;
  //   this.onChange(transformedValue);
  // }
  modelChange(value: string) {
    const dateValue = new Date(value);
    const transformedValue = `CUSTOM-DATE-VALUE-${dateValue.getFullYear()}-${dateValue.getMonth() +
    1}`;
    const transformedvalue1 = formatDate(value, "yyyy-MM-dd'T'HH:mm", 'en-US');
    // @ts-ignore
    this.onChange(transformedvalue1);
  }
}
