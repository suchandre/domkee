/*
 * Copyright 2008 JRimum Project
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * Created at: 30/03/2008 - 18:57:43
 * ================================================================================
 * Direitos autorais 2008 JRimum Project
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * Criado em: 30/03/2008 - 18:57:43
 */

package org.jrimum.domkee.financeiro.banco.febraban;

import static org.jrimum.utilix.Objects.isNotNull;

import java.awt.Image;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LogManager;import org.apache.logging.log4j.Logger;
import org.jrimum.domkee.comum.pessoa.contato.NumeroDeTelefone;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.id.cprf.CNPJ;
import org.jrimum.domkee.comum.pessoa.id.cprf.CPRF;
import org.jrimum.domkee.financeiro.banco.Pessoa;
import org.jrimum.domkee.financeiro.banco.PessoaJuridica;

/**
 * 
 * <p>
 * Um Banco (instituição financeira) supervisionada pelo <a href="http://www.bcb.gov.br/">BACEN</a>.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */

public class Banco implements org.jrimum.domkee.financeiro.banco.Banco {

	private static Logger log = LogManager.getLogger(Banco.class);

	private CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN;

	private String segmento;

	private Image imgLogo;

	private PessoaJuridica pessoaJuridica;

	/**
	 * 
	 */
	public Banco() {
		super();
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao) {
		super();

		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;

		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setNome(instituicao);
		pessoaJuridica.setNomeFantasia(instituicao);
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 * @param cnpj
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao, CNPJ cnpj) {
		super();

		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;

		pessoaJuridica = new PessoaJuridica();

		pessoaJuridica.setCPRF(cnpj);
		pessoaJuridica.setNome(instituicao);
		pessoaJuridica.setNomeFantasia(instituicao);
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 * @param cnpj
	 * @param segmento
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao, CNPJ cnpj, String segmento) {

		super();

		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;
		this.segmento = segmento;

		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCPRF(cnpj);
		pessoaJuridica.setNome(instituicao);
		pessoaJuridica.setNomeFantasia(instituicao);
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 * @param cnpj
	 * @param segmento
	 * @param imgLogo
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao, CNPJ cnpj, String segmento, Image imgLogo) {

		super();

		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;
		this.segmento = segmento;
		this.imgLogo = imgLogo;

		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCPRF(cnpj);
	}

	/**
	 * <p>
	 * Verifica se o código passado está ok em relação as regras:
	 * <ol>
	 * <li>Não nulo</li>
	 * <li>Numérico</li>
	 * <li>Com 3 digitos</li>
	 * </ol>
	 * </p>
	 * 
	 * @param codigo
	 *            - Código de compensação BACEN do banco
	 * 
	 * @return se ok
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @since 0.2
	 * 
	 */
	public static boolean isCodigoDeCompensacaoOK(String codigo) {

		boolean ok = false;

		if (isNotNull(codigo)) {

			if (StringUtils.isNumeric(codigo)) {

				if (codigo.length() == 3) {

					ok = true;

				} else {
					log.warn("O código é de apenas 3 digitos!");
				}

			} else {
				log.warn("O código de compensação deve ser numérico!");
			}
		}

		return ok;
	}

	/**
	 * @return the codigoDeCompensacaoBACEN
	 */
	@Override
	public CodigoDeCompensacaoBACEN getCodigoDeCompensacaoBACEN() {
		return codigoDeCompensacaoBACEN;
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 *            the codigoDeCompensacaoBACEN to set
	 */
	@Override
	public void setCodigoDeCompensacaoBACEN(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN) {
		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;
	}

	public CNPJ getCNPJ() {
		return (CNPJ) pessoaJuridica.getCPRF();
	}

	public void setCNPJ(CNPJ cnpj) {
		pessoaJuridica.setCPRF(cnpj);
	}

	@Override
	public String getSegmento() {
		return segmento;
	}

	@Override
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	@Override
	public Image getImgLogo() {
		return imgLogo;
	}

	@Override
	public void setImgLogo(Image imgLogo) {
		this.imgLogo = imgLogo;
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#addEndereco(org.jrimum.domkee.comum.pessoa.endereco.Endereco)
	 */

	@Override
	public void addEndereco(Endereco endereco) {

		pessoaJuridica.addEndereco(endereco);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#addTelefone(org.jrimum.domkee.comum.pessoa.contato.NumeroDeTelefone)
	 */

	@Override
	public void addTelefone(NumeroDeTelefone telefone) {

		pessoaJuridica.addTelefone(telefone);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#getCPRF()
	 */

	@Override
	public CPRF getCPRF() {

		return pessoaJuridica.getCPRF();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#getEnderecos()
	 */
	@Override
	public Collection<Endereco> getEnderecos() {

		return pessoaJuridica.getEnderecos();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#getNome()
	 */

	@Override
	public String getNome() {

		return pessoaJuridica.getNome();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#getTelefones()
	 */

	@Override
	public Collection<NumeroDeTelefone> getTelefones() {

		return pessoaJuridica.getTelefones();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#isFisica()
	 */

	@Override
	public boolean isFisica() {

		return pessoaJuridica.isFisica();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#isJuridica()
	 */

	@Override
	public boolean isJuridica() {

		return pessoaJuridica.isJuridica();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#setCPRF(org.jrimum.domkee.comum.pessoa.id.cprf.CPRF)
	 */

	@Override
	public void setCPRF(CPRF cprf) {

		pessoaJuridica.setCPRF(cprf);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#setEnderecos(java.util.Collection)
	 */
	@Override
	public void setEnderecos(Collection<Endereco> enderecos) {

		pessoaJuridica.setEnderecos(enderecos);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#setNome(java.lang.String)
	 */

	@Override
	public void setNome(String nome) {

		pessoaJuridica.setNome(nome);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#setTelefones(java.util.Collection)
	 */

	@Override
	public void setTelefones(Collection<NumeroDeTelefone> telefones) {

		pessoaJuridica.setTelefones(telefones);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.PessoaJuridica#getInscricaoEstadual()
	 */

	@Override
	public Long getInscricaoEstadual() {

		return pessoaJuridica.getInscricaoEstadual();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.PessoaJuridica#getInscricaoMunicipal()
	 */

	@Override
	public Long getInscricaoMunicipal() {

		return pessoaJuridica.getInscricaoMunicipal();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.PessoaJuridica#getNomeFantasia()
	 */

	@Override
	public String getNomeFantasia() {

		return pessoaJuridica.getNome();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.PessoaJuridica#setInscricaoEstadual(java.lang.Long)
	 */

	@Override
	public void setInscricaoEstadual(Long inscricaoEstadual) {

		pessoaJuridica.setInscricaoEstadual(inscricaoEstadual);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.PessoaJuridica#setInscricaoMunicipal(java.lang.Long)
	 */

	@Override
	public void setInscricaoMunicipal(Long inscricaoMunicipal) {

		pessoaJuridica.setInscricaoMunicipal(inscricaoMunicipal);
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.PessoaJuridica#setNomeFantasia(java.lang.String)
	 */

	@Override
	public void setNomeFantasia(String nomeFantasia) {

		pessoaJuridica.setNomeFantasia(nomeFantasia);
	}

	/**
	 * @see Pessoa#addContaBancaria(ContaBancaria)
	 */
	@Override
	public void addContaBancaria(ContaBancaria contaBancaria) {
		pessoaJuridica.addContaBancaria(contaBancaria);

	}

	/**
	 * @see Pessoa#getContasBancarias()
	 */

	@Override
	public Collection<ContaBancaria> getContasBancarias() {

		return pessoaJuridica.getContasBancarias();
	}

	/**
	 * @see Pessoa#hasContaBancaria()
	 */
	@Override
	public boolean hasContaBancaria() {

		return pessoaJuridica.hasContaBancaria();
	}

	/**
	 * @see org.jrimum.domkee.comum.pessoa.Pessoa#setContasBancarias(java.util.Collection)
	 */

	@Override
	public void setContasBancarias(Collection<ContaBancaria> contasBancarias) {

		pessoaJuridica.setContasBancarias(contasBancarias);
	}

	@Override
	public String toString() {

		ToStringBuilder tb = new ToStringBuilder(this);
		tb.append(codigoDeCompensacaoBACEN);
		tb.append(segmento);
		tb.append(pessoaJuridica);

		return tb.toString();
	}

}
