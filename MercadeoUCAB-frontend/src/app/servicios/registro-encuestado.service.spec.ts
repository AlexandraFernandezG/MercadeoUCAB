import { TestBed } from '@angular/core/testing';

import { RegistroEncuestadoService } from './registro-encuestado.service';

describe('RegistroEncuestadoService', () => {
  let service: RegistroEncuestadoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistroEncuestadoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
