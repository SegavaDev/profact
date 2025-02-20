import { TestBed } from '@angular/core/testing';

import { ObtenerConsecutivoFactService } from './obtener-consecutivo-fact.service';

describe('ObtenerConsecutivoFactService', () => {
  let service: ObtenerConsecutivoFactService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObtenerConsecutivoFactService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
