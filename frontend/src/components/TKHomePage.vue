<template>
  <div id="root">
    <div class="container">
      <div class="search-wrapper">
        <img class="search-logo" src="@assets/logo.png" alt="vue logo icon image" />
        <div class="search">
          <input
              v-bind:value="targetURL"
              v-on:input="targetURL = $event.target.value"
              class="search-textfield" type="text" placeholder="Insert web page URL" />
        </div>

        <button class="submit">
          <p v-on:click="actionSubmit">SUBMIT</p>
        </button>
      </div>

      <div class="info-wrapper">
        <div class="info">
          <div class="info-row info-head">
            <div>NO.</div>
            <div>Category</div>
            <div>Name</div>
            <div>Code</div>
            <div>Color</div>
            <div>Size</div>
            <div>Feature</div>
            <div>Country</div>
            <div>Date</div>
            <div>TAG Price</div>
            <div>Sale Price</div>
          </div>

          <div class="info-body">
            <div class="info-row info-content"
                 v-for="productInfo in this.productInfoList">
              <div class="info-no">{{ productInfo.no }}</div>
              <div class="info-category">{{productInfo.category}}</div>
              <div class="info-name">{{productInfo.name}}</div>
              <div class="info-code">{{productInfo.code}}</div>
              <div class="info-color">{{productInfo.color}}</div>
              <div class="info-size">{{productInfo.size}}</div>
              <div class="info-feature">{{productInfo.feature}}</div>
              <div class="info-country">{{productInfo.country}}</div>
              <div class="info-date">{{productInfo.date}}</div>
              <div class="info-tag">{{productInfo.tagPrice}}</div>
              <div class="info-sale">{{productInfo.salePrice}}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
// import * as webdriver from "selenium-webdriver";

export default {
  name: "TKHomePage",
  props: {

  },
  data: function() {
    return {
      targetURL: "",
      parsedURL: "",
      parsedQuery: {},
      productInfoList : []
    }
  },
  methods: {
    getURL() {
      return this.targetURL.match(/(http|https)\:\/\/.+[\w\W](?=\?)/g);
    },

    getQuery() {
      const parsedQuery = this.targetURL.match(/[\w]+(?=\=)|[\w]+((?=\&)|$)/g);
      let query = {};

      for (let i = 0; i < parsedQuery.length; i += 2) {
        query[parsedQuery[i]] = parsedQuery[i + 1];
      }

      return query;
    },

    getTargetHTML() {
      let parsedURLArray = this.getURL();

      if (parsedURLArray === null) {
        return;
      }

      this.parsedURL = parsedURLArray[0];
      this.parsedQuery = this.getQuery();

      if (this.targetURL.length > 0) {

      }
    },

    actionSubmit() {
      if (this.productInfoList.length > 0) {
        this.productInfoList = [];
      }

      this.getTargetHTML();

      // for (let i = 0; i < 5; i++) {
      //   this.productInfoList.push({
      //     no: i,
      //     category: "none",
      //     name: "none",
      //     code: "none",
      //     color: "none",
      //     size: "none",
      //     feature: "none",
      //     country: "none",
      //     date: "none",
      //     tagPrice: "none",
      //     salePrice: "none"
      //   });
      // }
    }
  },
};
</script>

<style src="./TKHomePage.css" />
