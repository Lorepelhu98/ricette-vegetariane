import { Component, OnInit } from '@angular/core';

interface Recipe {
  id: number;
  title: string;
  description: string;
  image: string;
}

interface Category {
  id: number;
  name: string;
  description: string;
}


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {

  latestRecipes: Recipe[] = [
    {
      id: 1,
      title: 'Risotto ai Funghi Porcini',
      description: 'Un risotto cremoso e ricco di sapore, perfetto per una cena elegante.',
      image: 'assets/images/risotto-funghi.jpg'
    },
    {
      id: 2,
      title: 'Insalata di Quinoa e Avocado',
      description: 'Una fresca e leggera insalata, ideale per un pranzo veloce e salutare.',
      image: 'assets/images/insalata-quinoa.jpg'
    },
    {
      id: 3,
      title: 'Curry di Verdure',
      description: 'Un piatto speziato e aromatico, ispirato alla cucina indiana.',
      image: 'assets/images/curry-verdure.jpg'
    }
  ];

  recipeCategories: Category[] = [
    {
      id: 1,
      name: 'Primi Piatti',
      description: 'Scopri le nostre ricette di primi piatti vegetariani, dai classici italiani alle innovazioni più moderne.'
    },
    {
      id: 2,
      name: 'Secondi Piatti',
      description: 'Piatti principali ricchi e saporiti che non faranno rimpiangere la carne.'
    },
    {
      id: 3,
      name: 'Dolci',
      description: 'Delizie vegetariane per concludere ogni pasto in dolcezza.'
    }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
