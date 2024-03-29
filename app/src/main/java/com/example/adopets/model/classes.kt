package com.example.adopets.model

import java.util.*

open class Usuario(
    open var email: String = "",
    open var senha: String = "",
    open var dataNasc: Date = Date(),
    open var foto: ByteArray = "".toByteArray(),
    open var telefone: String = "",
    open var nome: String = "",
    open var bairro: String = "",
    open var rua: String = "",
    open var numero: String = "",
    open var complemento: String = "",
    open var cep: String = ""
)

class Doador(override var email: String = "", override var senha: String = "",
             override var dataNasc: Date = Date(), override var foto: ByteArray = "".toByteArray(),
             override var telefone: String = "", override var nome: String = "", override var bairro: String = "",
             override var rua: String = "", override var numero: String = "", override var complemento: String = "",
             override var cep: String = ""): Usuario(email, senha, dataNasc, foto, telefone, nome, bairro, rua, numero, complemento, cep)

class Adotante(override var email: String = "", override var senha: String = "",
               override var dataNasc: Date = Date(), override var foto: ByteArray = "".toByteArray(),
               override var telefone: String = "", override var nome: String = "", override var bairro: String = "",
               override var rua: String = "", override var numero: String = "", override var complemento: String = "",
               override var cep: String = ""): Usuario(email, senha, dataNasc, foto, telefone, nome, bairro, rua, numero, complemento, cep)

class Voluntario(override var email: String = "", override var senha: String = "",
                 override var dataNasc: Date = Date(), override var foto: ByteArray = "".toByteArray(),
                 override var telefone: String = "", override var nome: String = "", override var bairro: String = "",
                 override var rua: String = "", override var numero: String = "", override var complemento: String = "",
                 override var cep: String = "", var pontuacao: Float = 0F): Usuario(email, senha, dataNasc, foto, telefone, nome, bairro, rua, numero, complemento, cep)

class Contratante(override var email: String = "", override var senha: String = "",
                  override var dataNasc: Date = Date(), override var foto: ByteArray = "".toByteArray(),
                  override var telefone: String = "", override var nome: String = "", override var bairro: String = "",
                  override var rua: String = "", override var numero: String = "", override var complemento: String = "",
                  override var cep: String = ""): Usuario(email, senha, dataNasc, foto, telefone, nome, bairro, rua, numero, complemento, cep)

class Processo(
    var codigo: Long = 0L,
    var motivo: String = "",
    var situacao: String = "",
    var dataCriacao: Date = Date(),
    var dataFim: Date = Date(),
    var doador: Doador = Doador()
)

class AdotanteProcesso(
    var adotante: Adotante = Adotante(),
    var processo: Processo = Processo()
)

class Servico(
    var codigo: Long = 0L,
    var tipo: String = "",
    var dataInicio: Date = Date(),
    var dataFim: Date = Date(),
    var descricao: String = "",
    var voluntario: Voluntario = Voluntario()
)

class ContratanteServico(
    var contratante: Contratante = Contratante(),
    var servico: Servico = Servico()
)

class Animal(
    var codigo: Long = 0L,
    var foto: ByteArray = "".toByteArray(),
    var situacao: String = "",
    var raca: String = "",
    var descricao: String = "",
    var tamanho: String = "",
    var sexo: String = "",
    var necessidade: String = "",
    var tipo: String = "",
    var nome: String = "",
    var dataNasc: Date = Date()
)

class AnimalProceso(
    var animal: Animal = Animal(),
    var processo: Processo = Processo()
)

class AnimalServico(
    var animal: Animal = Animal(),
    var servico: Servico = Servico()
)
