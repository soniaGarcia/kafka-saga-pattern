import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Apollo } from 'apollo-angular';
import gql from 'graphql-tag';

@Component({
  selector: 'consulta-ordenes',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent1 implements OnInit {

  data: any;

  constructor(private apollo: Apollo) { }

  ngOnInit() {

    this.apollo.use('endpoint2').query({
      query: gql`query {
        ordenes 
        {
          id,
          userId,
          productId,
          price,
          status
        }
      }`
    }).subscribe(({ data, loading }) => {
      this.data = data;
    });

  }
}
