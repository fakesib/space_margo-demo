$('nav a').click(function(e) {
    e.preventDefault();
    $('nav a').removeClass('active');
    $(this).addClass('active');
    if(this.id === !'payment'){
      $('.payment').addClass('noshow');
    }
    else if (this.id === 'profile') {
      $('.profile').removeClass('noshow');
       $('.right-box').children().not('.profile').addClass('noshow');
    }
    else if(this.id === 'settings') {
      $('.settings').removeClass('noshow');
      $('.right-box').children().not('.settings').addClass('noshow');
    }
  });