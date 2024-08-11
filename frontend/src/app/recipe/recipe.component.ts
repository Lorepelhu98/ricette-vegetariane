import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


interface Recipe {
  id: number;
  name: string;
  description: string;
}


@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrl: './recipe.component.scss'
})
export class RecipeComponent implements OnInit {
  recipes: Recipe[] = [
    { id: 1, name: 'Spaghetti al Pomodoro', description: 'Un classico della cucina italiana, preparato con pomodori freschi, basilico e un tocco di olio d\'oliva.' },
    { id: 2, name: 'Insalata Caprese', description: 'Un piatto fresco e leggero con pomodori, mozzarella di bufala, basilico e un filo di olio d\'oliva.' },
    { id: 3, name: 'Lasagna Vegetariana', description: 'Una deliziosa lasagna con strati di verdure miste, ricoperta di salsa di pomodoro e formaggio.' },
    { id: 4, name: 'Ratatouille', description: 'Uno stufato di verdure di origine francese, preparato con zucchine, melanzane, peperoni e pomodori.' },
    { id: 5, name: 'Curry di Ceci', description: 'Un piatto ricco e speziato con ceci, pomodori, latte di cocco e una miscela di spezie aromatiche.' }
  ];

  view: string = 'list';
  selectedRecipe: Recipe | null = null;
  recipeForm: Recipe = { id: 0, name: '', description: '' };
  editMode: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.selectedRecipe = this.recipes.find(r => r.id === +params['id']) || null;
        this.view = 'detail';
      } else if (this.view === 'form') {
        this.recipeForm = { id: this.recipes.length + 1, name: '', description: '' };
        this.editMode = false;
      }
    });
  }

  onSubmit(): void {
    if (this.editMode) {
      // Update recipe logic
      const index = this.recipes.findIndex(r => r.id === this.recipeForm.id);
      if (index !== -1) {
        this.recipes[index] = this.recipeForm;
      }
    } else {
      // Add new recipe logic
      this.recipes.push(this.recipeForm);
    }
    this.view = 'list';
  }
}
