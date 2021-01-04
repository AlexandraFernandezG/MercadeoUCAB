import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarsolicitudesComponent } from './listarsolicitudes.component';

describe('ListarsolicitudesComponent', () => {
  let component: ListarsolicitudesComponent;
  let fixture: ComponentFixture<ListarsolicitudesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarsolicitudesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarsolicitudesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
