// SALUD EN GENERAL

const allStars_salud = document.querySelectorAll(".star-salud");
let rating_salud = document.querySelector(".rating-salud");
allStars_salud.forEach((star, i) => {
  star.addEventListener("click", function (e) {
    e.preventDefault();
    let current_star_level = i + 1;
    let opcion = "";
    switch (current_star_level) {
      case 1:
        opcion = "Muy mala";
        break;
      case 2:
        opcion = "Mala";
        break;
      case 3:
        opcion = "Regular";
        break;
      case 4:
        opcion = "Buena";
        break;
      case 5:
        opcion = "Excelente";
        break;

      default:
        opcion = "Elegir";
        break;
    }

    //    rating_salud.innerText = `${opcion}`;

    rating_salud.value = opcion;
    allStars_salud.forEach((star, j) => {
      if (current_star_level >= j + 1) {
        star.innerHTML = "&#9733";
      } else {
        star.innerHTML = "&#9734";
      }
    });
  });
});

// CONDICIÓN FISICA
const allStarsFisico = document.querySelectorAll(".star-fisico");
let rating_fisico = document.querySelector(".rating-fisico");
allStarsFisico.forEach((star, i) => {
  star.addEventListener("click", function (e) {
    e.preventDefault();
    let current_star_level = i + 1;
    let opcion = "";
    switch (current_star_level) {
      case 1:
        opcion = "Muy mala";
        break;
      case 2:
        opcion = "Mala";
        break;
      case 3:
        opcion = "Regular";
        break;
      case 4:
        opcion = "Buena";
        break;
      case 5:
        opcion = "Excelente";
        break;

      default:
        opcion = "Elegir";
        break;
    }

    // rating_fisico.innerText = `${opcion}`;
    rating_fisico.value = opcion;
    allStarsFisico.forEach((star, j) => {
      if (current_star_level >= j + 1) {
        star.innerHTML = "&#9733";
      } else {
        star.innerHTML = "&#9734";
      }
    });
  });
});

// CONDICIÓN NUTRICIONAL
const allStarsNutricion = document.querySelectorAll(".star-nutricion");
let rating_nutricion = document.querySelector(".rating-nutricion");
allStarsNutricion.forEach((star, i) => {
  star.addEventListener("click", function (e) {
    e.preventDefault();
    let current_star_level = i + 1;
    let opcion = "";
    switch (current_star_level) {
      case 1:
        opcion = "Muy mala";
        break;
      case 2:
        opcion = "Mala";
        break;
      case 3:
        opcion = "Regular";
        break;
      case 4:
        opcion = "Buena";
        break;
      case 5:
        opcion = "Excelente";
        break;

      default:
        opcion = "Elegir";
        break;
    }

    // rating_nutricion.innerText = `${opcion}`;
    rating_nutricion.value = opcion;
    allStarsNutricion.forEach((star, j) => {
      if (current_star_level >= j + 1) {
        star.innerHTML = "&#9733";
      } else {
        star.innerHTML = "&#9734";
      }
    });
  });
});

// CONDICIÓN NUTRICIONAL
const allStarsEstres = document.querySelectorAll(".star-estres");
let rating_estres = document.querySelector(".rating-estres");
allStarsEstres.forEach((star, i) => {
  star.addEventListener("click", function (e) {
    e.preventDefault();
    let current_star_level = i + 1;
    let opcion = "";
    switch (current_star_level) {
      case 1:
        opcion = "Muy mala";
        break;
      case 2:
        opcion = "Mala";
        break;
      case 3:
        opcion = "Regular";
        break;
      case 4:
        opcion = "Buena";
        break;
      case 5:
        opcion = "Excelente";
        break;

      default:
        opcion = "Elegir";
        break;
    }
    rating_estres.value = opcion;
    //    rating_estres.innerText = `${opcion}`;

    allStarsEstres.forEach((star, j) => {
      if (current_star_level >= j + 1) {
        star.innerHTML = "&#9733";
      } else {
        star.innerHTML = "&#9734";
      }
    });
  });
});

