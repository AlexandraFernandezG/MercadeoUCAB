import { TestBed } from '@angular/core/testing';

import { TiposPresentacionService } from './tipospresentaciones.service';

describe('TipoPresentacionService', () => {
  let service: TiposPresentacionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TiposPresentacionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
