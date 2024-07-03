import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeFavoritesComponent } from './recipe-favorites.component';

describe('RecipeFavoritesComponent', () => {
  let component: RecipeFavoritesComponent;
  let fixture: ComponentFixture<RecipeFavoritesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RecipeFavoritesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecipeFavoritesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
