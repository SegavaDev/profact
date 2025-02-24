import { TestBed } from '@angular/core/testing';

import { GenerarFacturaService } from './generar-factura.service';

describe('GenerarFacturaService', () => {
  let service: GenerarFacturaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GenerarFacturaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
