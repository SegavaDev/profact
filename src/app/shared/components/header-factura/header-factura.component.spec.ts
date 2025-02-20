import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderFacturaComponent } from './header-factura.component';

describe('HeaderFacturaComponent', () => {
  let component: HeaderFacturaComponent;
  let fixture: ComponentFixture<HeaderFacturaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderFacturaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderFacturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
