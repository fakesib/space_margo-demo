var submitButton = document.getElementById('submitButton');
const form = document.querySelector("form"),
  emailField = form.querySelector(".email-field"),
  emailInput = emailField.querySelector(".email"),
  passField = form.querySelector(".create-password"),
  passInput = passField.querySelector(".password"),
  cPassField = form.querySelector(".confirm-password"),
  cPassInput = cPassField.querySelector(".confirm");
  


emailInput.addEventListener("input", checkEmail);
passInput.addEventListener("input", createPassword);
cPassInput.addEventListener("input", confirmPassword);

function sendData() {
  const XHR = new XMLHttpRequest();
  const FD = new FormData(form);

  emailInput.addEventListener("keyup", checkEmail);
  passInput.addEventListener("keyup", createPassword);
  cPassInput.addEventListener("keyup", confirmPassword);

  XHR.addEventListener("load", function (event) {
    document.location.href = "/login";
  });

  XHR.addEventListener("error", function (event) {
    alert('Упссс... Что-то пошло не так!');
  });

  XHR.open("POST", "/registration");

  XHR.send(FD);
}

function checkEmail() {
  const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
  if (!emailInput.value.match(emailPattern)) {
    return emailField.classList.add("invalid");
  }
  emailField.classList.remove("invalid");
}

function createPassword() {
  const passwordPattern = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d@$!%*?&]{6,}$/;
  if (!passInput.value.match(passwordPattern)) {
    return passField.classList.add("invalid");
  }
  passField.classList.remove("invalid");
}

function confirmPassword() {
  if (passInput.value !== cPassInput.value || cPassInput.value === "") {
    return cPassField.classList.add("invalid");
  }
  cPassField.classList.remove("invalid");
}


form.addEventListener("submit", (e) => {
  const xhr = new XMLHttpRequest();
  const passwordPattern = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d@$!%*?&]{6,}$/;
  const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
  const method = form.method;
  const action = form.getAttribute('th:action');
  const formData = new FormData(form);



  e.preventDefault();


  xhr.open(method, action, true);

  xhr.addEventListener("load", function (event) {
    sendData();
  });

  xhr.addEventListener("error", function (event) {
    alert('Что-то пошло не так!');
  });

  if (passInput.value.match(passwordPattern) && passInput.value === cPassInput.value && emailInput.value.match(emailPattern)) {
    xhr.send(formData);
      submitButton.classList.add("clicked");
      submitButton.disabled = true;   
  }
});
