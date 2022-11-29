
    create table tb_categoria (
       id  bigserial not null,
        nome varchar(255),
        primary key (id)
    );

    create table tb_cidade (
       id  bigserial not null,
        nome varchar(255),
        id_estado int8,
        primary key (id)
    );

    create table tb_cliente (
       id  bigserial not null,
        cpf_ou_cnpj varchar(255),
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        tipo int4,
        primary key (id)
    );

    create table tb_endereco (
       id  bigserial not null,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        logradouro varchar(255),
        numero varchar(255),
        id_cidade int8,
        id_cliente int8,
        primary key (id)
    );

    create table tb_estado (
       id  bigserial not null,
        nome varchar(255),
        primary key (id)
    );

    create table tb_item_pedido (
       desconto float8,
        preco float8,
        quantidade int4,
        id_produto int8 not null,
        id_pedido int8 not null,
        primary key (id_pedido, id_produto)
    );

    create table tb_pagamento (
       id_pedido int8 not null,
        estado int4,
        primary key (id_pedido)
    );

    create table tb_pagamento_com_boleto (
       data_pagamento timestamp,
        data_vencimento timestamp,
        id_pedido int8 not null,
        primary key (id_pedido)
    );

    create table tb_pagamento_com_cartao (
       numero_parcelas int4,
        id_pedido int8 not null,
        primary key (id_pedido)
    );

    create table tb_pedido (
       id  bigserial not null,
        instante timestamp,
        id_cliente int8,
        id_endereco_entrega int8,
        primary key (id)
    );

    create table tb_produto (
       id  bigserial not null,
        nome varchar(255),
        preco float8,
        primary key (id)
    );

    create table tb_produto_categoria (
       id_produto int8 not null,
        id_categoria int8 not null
    );

    create table tb_telefone (
       cliente_id int8 not null,
        telefones varchar(255)
    );

    alter table tb_cliente 
       add constraint UK_ir2m70agseiyyajtaxq7wsw20 unique (email);

    alter table tb_cidade 
       add constraint FK12tfv6wlsvaml7nsmq106hvib 
       foreign key (id_estado) 
       references tb_estado;

    alter table tb_endereco 
       add constraint FK1wlvil0y56h89uki97895gl5o 
       foreign key (id_cidade) 
       references tb_cidade;

    alter table tb_endereco 
       add constraint FKfybceun95o8l6dq559fji2q8n 
       foreign key (id_cliente) 
       references tb_cliente;

    alter table tb_item_pedido 
       add constraint FK7vkkyy2auypwoiiouck1qu2bd 
       foreign key (id_produto) 
       references tb_produto;

    alter table tb_item_pedido 
       add constraint FK9hmxwx5o13b45x76qiwcdfwcj 
       foreign key (id_pedido) 
       references tb_pedido;

    alter table tb_pagamento 
       add constraint FKfjxvx0nnboqqvlpl9xrcjp51x 
       foreign key (id_pedido) 
       references tb_pedido;

    alter table tb_pagamento_com_boleto 
       add constraint FK1e7643dpib2jxi7bg9e99op3a 
       foreign key (id_pedido) 
       references tb_pagamento;

    alter table tb_pagamento_com_cartao 
       add constraint FKi5fym6nkg700ko8mxson7omxw 
       foreign key (id_pedido) 
       references tb_pagamento;

    alter table tb_pedido 
       add constraint FKmjqm65wiaj65gia070a45vt9w 
       foreign key (id_cliente) 
       references tb_cliente;

    alter table tb_pedido 
       add constraint FK5od1b08yi00ey5lr6sk2lpp0e 
       foreign key (id_endereco_entrega) 
       references tb_endereco;

    alter table tb_produto_categoria 
       add constraint FKqtt9g668uolxpjd42rdb6g7kk 
       foreign key (id_categoria) 
       references tb_categoria;

    alter table tb_produto_categoria 
       add constraint FKsf16c80ybln49mop4yex7v7pg 
       foreign key (id_produto) 
       references tb_produto;

    alter table tb_telefone 
       add constraint FKpwjwudqbv75e49ux295dm87al 
       foreign key (cliente_id) 
       references tb_cliente;

    create table tb_categoria (
       id  bigserial not null,
        nome varchar(255),
        primary key (id)
    );

    create table tb_cidade (
       id  bigserial not null,
        nome varchar(255),
        id_estado int8,
        primary key (id)
    );

    create table tb_cliente (
       id  bigserial not null,
        cpf_ou_cnpj varchar(255),
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        tipo int4,
        primary key (id)
    );

    create table tb_endereco (
       id  bigserial not null,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        logradouro varchar(255),
        numero varchar(255),
        id_cidade int8,
        id_cliente int8,
        primary key (id)
    );

    create table tb_estado (
       id  bigserial not null,
        nome varchar(255),
        primary key (id)
    );

    create table tb_item_pedido (
       desconto float8,
        preco float8,
        quantidade int4,
        id_produto int8 not null,
        id_pedido int8 not null,
        primary key (id_pedido, id_produto)
    );

    create table tb_pagamento (
       id_pedido int8 not null,
        estado int4,
        primary key (id_pedido)
    );

    create table tb_pagamento_com_boleto (
       data_pagamento timestamp,
        data_vencimento timestamp,
        id_pedido int8 not null,
        primary key (id_pedido)
    );

    create table tb_pagamento_com_cartao (
       numero_parcelas int4,
        id_pedido int8 not null,
        primary key (id_pedido)
    );

    create table tb_pedido (
       id  bigserial not null,
        instante timestamp,
        id_cliente int8,
        id_endereco_entrega int8,
        primary key (id)
    );

    create table tb_produto (
       id  bigserial not null,
        nome varchar(255),
        preco float8,
        primary key (id)
    );

    create table tb_produto_categoria (
       id_produto int8 not null,
        id_categoria int8 not null
    );

    create table tb_telefone (
       cliente_id int8 not null,
        telefones varchar(255)
    );

    alter table tb_cliente 
       add constraint UK_ir2m70agseiyyajtaxq7wsw20 unique (email);

    alter table tb_cidade 
       add constraint FK12tfv6wlsvaml7nsmq106hvib 
       foreign key (id_estado) 
       references tb_estado;

    alter table tb_endereco 
       add constraint FK1wlvil0y56h89uki97895gl5o 
       foreign key (id_cidade) 
       references tb_cidade;

    alter table tb_endereco 
       add constraint FKfybceun95o8l6dq559fji2q8n 
       foreign key (id_cliente) 
       references tb_cliente;

    alter table tb_item_pedido 
       add constraint FK7vkkyy2auypwoiiouck1qu2bd 
       foreign key (id_produto) 
       references tb_produto;

    alter table tb_item_pedido 
       add constraint FK9hmxwx5o13b45x76qiwcdfwcj 
       foreign key (id_pedido) 
       references tb_pedido;

    alter table tb_pagamento 
       add constraint FKfjxvx0nnboqqvlpl9xrcjp51x 
       foreign key (id_pedido) 
       references tb_pedido;

    alter table tb_pagamento_com_boleto 
       add constraint FK1e7643dpib2jxi7bg9e99op3a 
       foreign key (id_pedido) 
       references tb_pagamento;

    alter table tb_pagamento_com_cartao 
       add constraint FKi5fym6nkg700ko8mxson7omxw 
       foreign key (id_pedido) 
       references tb_pagamento;

    alter table tb_pedido 
       add constraint FKmjqm65wiaj65gia070a45vt9w 
       foreign key (id_cliente) 
       references tb_cliente;

    alter table tb_pedido 
       add constraint FK5od1b08yi00ey5lr6sk2lpp0e 
       foreign key (id_endereco_entrega) 
       references tb_endereco;

    alter table tb_produto_categoria 
       add constraint FKqtt9g668uolxpjd42rdb6g7kk 
       foreign key (id_categoria) 
       references tb_categoria;

    alter table tb_produto_categoria 
       add constraint FKsf16c80ybln49mop4yex7v7pg 
       foreign key (id_produto) 
       references tb_produto;

    alter table tb_telefone 
       add constraint FKpwjwudqbv75e49ux295dm87al 
       foreign key (cliente_id) 
       references tb_cliente;

    create table tb_categoria (
       id  bigserial not null,
        nome varchar(255),
        primary key (id)
    );

    create table tb_cidade (
       id  bigserial not null,
        nome varchar(255),
        id_estado int8,
        primary key (id)
    );

    create table tb_cliente (
       id  bigserial not null,
        cpf_ou_cnpj varchar(255),
        email varchar(255),
        nome varchar(255),
        senha varchar(255),
        tipo int4,
        primary key (id)
    );

    create table tb_endereco (
       id  bigserial not null,
        bairro varchar(255),
        cep varchar(255),
        complemento varchar(255),
        logradouro varchar(255),
        numero varchar(255),
        id_cidade int8,
        id_cliente int8,
        primary key (id)
    );

    create table tb_estado (
       id  bigserial not null,
        nome varchar(255),
        primary key (id)
    );

    create table tb_item_pedido (
       desconto float8,
        preco float8,
        quantidade int4,
        id_produto int8 not null,
        id_pedido int8 not null,
        primary key (id_pedido, id_produto)
    );

    create table tb_pagamento (
       id_pedido int8 not null,
        estado int4,
        primary key (id_pedido)
    );

    create table tb_pagamento_com_boleto (
       data_pagamento timestamp,
        data_vencimento timestamp,
        id_pedido int8 not null,
        primary key (id_pedido)
    );

    create table tb_pagamento_com_cartao (
       numero_parcelas int4,
        id_pedido int8 not null,
        primary key (id_pedido)
    );

    create table tb_pedido (
       id  bigserial not null,
        instante timestamp,
        id_cliente int8,
        id_endereco_entrega int8,
        primary key (id)
    );

    create table tb_produto (
       id  bigserial not null,
        nome varchar(255),
        preco float8,
        primary key (id)
    );

    create table tb_produto_categoria (
       id_produto int8 not null,
        id_categoria int8 not null
    );

    create table tb_telefone (
       cliente_id int8 not null,
        telefones varchar(255)
    );

    alter table tb_cliente 
       add constraint UK_ir2m70agseiyyajtaxq7wsw20 unique (email);

    alter table tb_cidade 
       add constraint FK12tfv6wlsvaml7nsmq106hvib 
       foreign key (id_estado) 
       references tb_estado;

    alter table tb_endereco 
       add constraint FK1wlvil0y56h89uki97895gl5o 
       foreign key (id_cidade) 
       references tb_cidade;

    alter table tb_endereco 
       add constraint FKfybceun95o8l6dq559fji2q8n 
       foreign key (id_cliente) 
       references tb_cliente;

    alter table tb_item_pedido 
       add constraint FK7vkkyy2auypwoiiouck1qu2bd 
       foreign key (id_produto) 
       references tb_produto;

    alter table tb_item_pedido 
       add constraint FK9hmxwx5o13b45x76qiwcdfwcj 
       foreign key (id_pedido) 
       references tb_pedido;

    alter table tb_pagamento 
       add constraint FKfjxvx0nnboqqvlpl9xrcjp51x 
       foreign key (id_pedido) 
       references tb_pedido;

    alter table tb_pagamento_com_boleto 
       add constraint FK1e7643dpib2jxi7bg9e99op3a 
       foreign key (id_pedido) 
       references tb_pagamento;

    alter table tb_pagamento_com_cartao 
       add constraint FKi5fym6nkg700ko8mxson7omxw 
       foreign key (id_pedido) 
       references tb_pagamento;

    alter table tb_pedido 
       add constraint FKmjqm65wiaj65gia070a45vt9w 
       foreign key (id_cliente) 
       references tb_cliente;

    alter table tb_pedido 
       add constraint FK5od1b08yi00ey5lr6sk2lpp0e 
       foreign key (id_endereco_entrega) 
       references tb_endereco;

    alter table tb_produto_categoria 
       add constraint FKqtt9g668uolxpjd42rdb6g7kk 
       foreign key (id_categoria) 
       references tb_categoria;

    alter table tb_produto_categoria 
       add constraint FKsf16c80ybln49mop4yex7v7pg 
       foreign key (id_produto) 
       references tb_produto;

    alter table tb_telefone 
       add constraint FKpwjwudqbv75e49ux295dm87al 
       foreign key (cliente_id) 
       references tb_cliente;
