import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


interface Recipe {
  id: number;
  name: string;
  description: string;
  imageUrl?: string;
}


@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrl: './recipe.component.scss'
})
export class RecipeComponent implements OnInit {
  recipes: Recipe[] = [
    { id: 1, name: 'Spaghetti al Pomodoro', description: 'Un classico della cucina italiana, preparato con pomodori freschi, basilico e un tocco di olio d\'oliva.', imageUrl: 'https://upload.wikimedia.org/wikipedia/commons/2/2a/Spaghetti_al_Pomodoro.JPG' },
    { id: 2, name: 'Insalata Caprese', description: 'Un piatto fresco e leggero con pomodori, mozzarella di bufala, basilico e un filo di olio d\'oliva.', imageUrl:'https://th.bing.com/th?id=OSK.c87b1fb5d0756857357a04f2c3ccc438&w=164&h=93&rs=2&qlt=80&o=6&cdv=1&dpr=1.3&pid=16.1' },
    { id: 3, name: 'Lasagna Vegetariana', description: 'Una deliziosa lasagna con strati di verdure miste, ricoperta di salsa di pomodoro e formaggio.',  imageUrl:'https://misrecetasdecocina.net/wp-content/uploads/2017/09/lasa%C3%B1a-vegetariana.jpg' },
    { id: 4, name: 'Ratatouille', description: 'Uno stufato di verdure di origine francese, preparato con zucchine, melanzane, peperoni e pomodori.',  imageUrl:'https://www.liveeatlearn.com/wp-content/uploads/2017/01/ratatouille-vegetable-tian-6.jpg' },
    { id: 5, name: 'Curry di Ceci', description: 'Un piatto ricco e speziato con ceci, pomodori, latte di cocco e una miscela di spezie aromatiche.',  imageUrl:'https://blog.giallozafferano.it/ricettandocondany/wp-content/uploads/2023/05/EOS-6D-Mark-II_7048ott.jpg' },
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
      const index = this.recipes.findIndex(r => r.id === this.recipeForm.id);
      if (index !== -1) {
        this.recipes[index] = this.recipeForm;
      }
    } else {
      this.recipes.push(this.recipeForm);
    }
    this.view = 'list';
  }
}
