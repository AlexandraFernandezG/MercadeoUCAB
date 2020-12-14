import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPresentacionComponent } from './add-presentacion.component';

describe('AddPresentacionComponent', () => {
  let component: AddPresentacionComponent;
  let fixture: ComponentFixture<AddPresentacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPresentacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPresentacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
