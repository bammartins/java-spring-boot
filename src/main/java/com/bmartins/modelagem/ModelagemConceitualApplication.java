package com.bmartins.modelagem;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bmartins.modelagem.domain.Categoria;
import com.bmartins.modelagem.domain.Cidade;
import com.bmartins.modelagem.domain.Cliente;
import com.bmartins.modelagem.domain.Endereco;
import com.bmartins.modelagem.domain.Estado;
import com.bmartins.modelagem.domain.ItemPedido;
import com.bmartins.modelagem.domain.Pagamento;
import com.bmartins.modelagem.domain.PagamentoBoleto;
import com.bmartins.modelagem.domain.PagamentoCartao;
import com.bmartins.modelagem.domain.Pedido;
import com.bmartins.modelagem.domain.Produto;
import com.bmartins.modelagem.domain.enums.EstadoPagamento;
import com.bmartins.modelagem.domain.enums.TipoCliente;
import com.bmartins.modelagem.repositories.CategoriaRepository;
import com.bmartins.modelagem.repositories.CidadeRepository;
import com.bmartins.modelagem.repositories.ClienteRepository;
import com.bmartins.modelagem.repositories.EnderecoRepository;
import com.bmartins.modelagem.repositories.EstadoRepository;
import com.bmartins.modelagem.repositories.ItemPedidoRepository;
import com.bmartins.modelagem.repositories.PagamentoRepository;
import com.bmartins.modelagem.repositories.PedidoRepository;
import com.bmartins.modelagem.repositories.ProdutoRepository;

@SpringBootApplication
public class ModelagemConceitualApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "PC", 5000.00);
		Produto p2 = new Produto(null, "Mouse", 500.00);
		Produto p3 = new Produto(null, "Mesa", 50.00);
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", e1);
		Cidade c2 = new Cidade(null, "Sao Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		
		Cliente cl1 = new Cliente(null, "Bruno Martins", "bruno@teste.com", TipoCliente.PESSOAFISICA);
		Cliente cl2 = new Cliente(null, "Bruno Martins Empresa", "bruno.empresa@teste.com", TipoCliente.PESSOAJURIDICA);
		
		Endereco end1 = new Endereco(null, "Rua Canaa", "07", null, "Jd. Santo André", "09132470", c2, cl1);
		Endereco end2 = new Endereco(null, "Rua Felipe Camarao", "507", null, "Utinga", "0000000", c2, cl1);
		Endereco end3 = new Endereco(null, "Alameda Cleveland", "509", null, "Jd. Santo André", "09132470", c1, cl2);
		Endereco end4 = new Endereco(null, "Rua Felipe Camarao", "507", null, "Utinga", "0000000", c3, cl2);
		
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, end2);
		
		
		Pagamento pg1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pg2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("17/05/2019 10:20"), new Date(System.currentTimeMillis()));
		
		ItemPedido ip1= new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2= new ItemPedido(ped1, p2, 0.00, 2, 80.00);
		ItemPedido ip3= new ItemPedido(ped2, p1, 0.00, 7, 2000.00);
		ItemPedido ip4= new ItemPedido(ped2, p2, 50.00, 2, 80.00);
		cl1.getTelefones().addAll(Arrays.asList("985701913", "49744449"));
		cl1.getEnderecos().addAll(Arrays.asList(end1, end4));
		
		cl2.getTelefones().addAll(Arrays.asList("968372707", "44510485"));
		cl2.getEnderecos().addAll(Arrays.asList(end2, end3));
		
//		Codigo Arthur, retorna uma lista de bairros cadastrados para o cliente x
		List<String> bairros =  cl2.getEnderecos().stream().map((Function<Endereco,String>) a ->{
			return a.getBairro();
			}).collect(Collectors.toList());
		System.out.println(bairros);
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		ped1.setPagamento(pg1);
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.setPagamento(pg2);
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		clienteRepository.saveAll(Arrays.asList(cl1, cl2));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pg1, pg2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4));
		
	}
	
	

}
