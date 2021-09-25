# com.tpirates.test

1. 설치 및 환경설정
- git clone 할 디렉토리로 이동
- git clone -b master https://github.com/dudeoeoeo/com.tpirates.test.git

개발 환경
- java 8 
- intellij -> File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing 체크

2. 테이블 SQL (application.properties 파일에 ddl_auto = create 로 되어 있기 때문에 별도 생성 불필요)

create table delivery (
        id bigint not null auto_increment,
        create_date datetime,
        modified_date datetime,
        closing varchar(255),
        type varchar(255),
        primary key (id)
    ) engine=InnoDB
    
    create table option (
       id bigint not null auto_increment,
        create_date datetime,
        modified_date datetime,
        name varchar(255),
        price integer not null,
        stock integer not null,
        product_id bigint,
        primary key (id)
    ) engine=InnoDB
    
    create table product (
       id bigint not null auto_increment,
        create_date datetime,
        modified_date datetime,
        description varchar(255),
        name varchar(255),
        delivery_id bigint,
        store_id bigint,
        primary key (id)
    ) engine=InnoDB
    
    create table store (
       id bigint not null auto_increment,
        create_date datetime,
        modified_date datetime,
        primary key (id)
    ) engine=InnoDB
    
    alter table option 
       add constraint FK5t6etuqa4wl7lyn0ysxnts7q4 
       foreign key (product_id) 
       references product (id)
    
    alter table product 
       add constraint FKatuyf608tp6xuxg8nvjfpuud6 
       foreign key (delivery_id) 
       references delivery (id)
    
    alter table product 
       add constraint FKjlfidudl1gwqem0flrlomvlcl 
       foreign key (store_id) 
       references store (id)
       
3. API 사용법

상품 추가 API

POST URL -> http://localhost:8080/api/v1/product_add 

JSON Body
{
  "name": "노르웨이산 연어",
  "description": "노르웨이산 연어 300g, 500g, 반마리 필렛",
  "delivery": {
  "type": "fast",
  "closing": "12:00" 
},
  "options": [
    {
      "name": "생연어 몸통살 300g",
      "price": 10000,
      "stock": 99
    },
    {
      "name": "생연어 몸통살 500g",
      "price": 17000,
      "stock": 99
    }
  ]
}

상품 목록 조회 API

GET http://localhost:8080/api/v1/products

상품 상세 조회 API

GET http://localhost:8080/api/v1/delivery_date/{id}

수령일 선택 목록 API

GET http://localhost:8080/api/v1/delivery_date/{id}

점포 삭제 API

DELETE http://localhost:8080/api/v1/remove/{id}

