package com.graphqljava.tutorial.bookdetails;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.language.ObjectTypeDefinition;
import graphql.language.TypeDefinition;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.PropertyDataFetcher;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {


    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;
    private GraphQLSchema schema;
    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema (1).graphql");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.schema = graphQLSchema;
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);

        RuntimeWiring runtimeWiring = buildWiring(typeRegistry);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring(TypeDefinitionRegistry typeRegistry) {
        TypeRuntimeWiring.Builder typeWiring = newTypeWiring("Query");
        RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring();

        ObjectTypeDefinition definition = (ObjectTypeDefinition)typeRegistry.types().get("Query");

        for(String type : definition.getFieldDefinitions().stream().map(d->d.getName()).collect(Collectors.toList())) {
            typeWiring = typeWiring.dataFetcher(type, new MyDataFethcer(type));
        }

        TypeRuntimeWiring typeRuntimeWiring = typeWiring.build();
        return builder.type(typeRuntimeWiring).build();
    }

}
