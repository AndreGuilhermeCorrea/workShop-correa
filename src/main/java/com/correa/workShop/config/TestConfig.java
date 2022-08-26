package com.correa.workShop.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.correa.workShop.entities.Categoria;
import com.correa.workShop.entities.Pagamento;
import com.correa.workShop.entities.Pedido;
import com.correa.workShop.entities.PedidoItens;
import com.correa.workShop.entities.Produto;
import com.correa.workShop.entities.Usuario;
import com.correa.workShop.entities.enums.PedidoStatus;
import com.correa.workShop.repositories.CategoriaRepository;
import com.correa.workShop.repositories.PedidoItensRepository;
import com.correa.workShop.repositories.PedidoRepository;
import com.correa.workShop.repositories.ProdutoRepository;
import com.correa.workShop.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	@Autowired
	private UsuarioRepository userRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PedidoItensRepository pedidoItensRepository;

	@Override
	public void run(String... args) throws Exception {
		
		
		//instanciação das categorias
		Categoria cat1 = new Categoria(null, "Electronics");
		Categoria cat2 = new Categoria(null, "Books");
		Categoria cat3 = new Categoria(null, "Computers");
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		Produto p1 = new Produto(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Produto p2 = new Produto(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Produto p3 = new Produto(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Produto p4 = new Produto(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Produto p5 = new Produto(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategorias().add(cat2);
		p2.getCategorias().add(cat1);
		p2.getCategorias().add(cat3);
		p3.getCategorias().add(cat3);
		p4.getCategorias().add(cat3);
		p5.getCategorias().add(cat2);
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		
		//instanciação usuários
		Usuario u1 = new Usuario(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		Usuario u2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		Usuario u3 = new Usuario(null, "Andre Guilherme Correa", "de_correa@msn.com", "19992489895", "123456");
		userRepository.saveAll(Arrays.asList(u1, u2,u3));
		
		//instanciação pedidos com datas no formato iso 8601
		Pedido o1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"),PedidoStatus.PAID, u1);
		Pedido o2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), PedidoStatus.WAITING_PAYMENT, u2);
		Pedido o3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"),PedidoStatus.DELIVERED, u1);
		Pedido o4 = new Pedido(null, Instant.parse("2019-08-22T15:21:25Z"),PedidoStatus.CANCELED, u3);
		pedidoRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
		
		PedidoItens oi1 = new PedidoItens(o1, p1, 2, p1.getPreco());
		PedidoItens oi2 = new PedidoItens(o1, p3, 1, p3.getPreco());
		PedidoItens oi3 = new PedidoItens(o2, p3, 2, p3.getPreco());
		PedidoItens oi4 = new PedidoItens(o3, p5, 2, p5.getPreco()); 
		pedidoItensRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

		Pagamento pay1 = new Pagamento(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		//associação de mao dupla em memória
		o1.setPagamento(pay1);

		pedidoRepository.save(o1);
		
	}

}
