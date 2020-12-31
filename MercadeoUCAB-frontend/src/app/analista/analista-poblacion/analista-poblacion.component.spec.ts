import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalistaPoblacionComponent } from './analista-poblacion.component';

describe('AnalistaPoblacionComponent', () => {
  let component: AnalistaPoblacionComponent;
  let fixture: ComponentFixture<AnalistaPoblacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnalistaPoblacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalistaPoblacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
