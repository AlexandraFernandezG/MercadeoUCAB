import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstudiosSugeridosComponent } from './estudios-sugeridos.component';

describe('EstudiosSugeridosComponent', () => {
  let component: EstudiosSugeridosComponent;
  let fixture: ComponentFixture<EstudiosSugeridosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstudiosSugeridosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EstudiosSugeridosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
