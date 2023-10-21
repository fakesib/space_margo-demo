const profileBtn = document.getElementById('profile');
const childBtn = document.getElementById('child');
const configBtn = document.getElementById('config');
const profileDiv = document.getElementById('profile-div');
const childDiv = document.getElementById('child-div');
const configDiv = document.getElementById('config-div');

   configDiv.classList.add('noshow');
   childDiv.classList.add('noshow');


// Обработчик события клика на кнопке "profile"
profileBtn.addEventListener('click', function() {
  profileBtn.classList.add('active');
  profileDiv.classList.remove('noshow');
  configDiv.classList.add('noshow');
  childDiv.classList.add('noshow');
  childBtn.classList.remove('active');
  configBtn.classList.remove('active');
});

// Обработчик события клика на кнопке "child"
childBtn.addEventListener('click', function() {
  childBtn.classList.add('active');
  childDiv.classList.remove('noshow');
  configBtn.classList.remove('active');
  profileBtn.classList.remove('active');
  configDiv.classList.add('noshow');
  profileDiv.classList.add('noshow');
});

// Обработчик события клика на кнопке "config"
configBtn.addEventListener('click', function() {
  configBtn.classList.add('active');
  configDiv.classList.remove('noshow');
  childBtn.classList.remove('active');
    profileBtn.classList.remove('active');
    childDiv.classList.add('noshow');
    profileDiv.classList.add('noshow');
});