#language: pt

  Funcionalidade: Realizar comprar

    Cenário: Efetuar comprar com sucesso
      Dado que estou na tela de produtos
      Quando adiciono alguns produtos ao carrinho
      E abro carrinho de compras
      E realizo o checkout
      Então devo conseguir ver a mensagem "Thank you for your order"