#language: pt

Funcionalidade: : Cadastro de produto
  Cenario: Cadastrar produto
    Dado que tenho um produto válido
    E envio uma requisição POST para criar o produto
    Entao o sistema deve retornar o status created