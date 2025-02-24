import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrillaFacturaComponent } from './grilla-factura.component';

describe('GrillaFacturaComponent', () => {
  let component: GrillaFacturaComponent;
  let fixture: ComponentFixture<GrillaFacturaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GrillaFacturaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GrillaFacturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
