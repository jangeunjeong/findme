<template>
  <v-container fluid grid-list-xs style="background-color: #f5f5f5">
    <v-layout row wrap style="background-color: #f5f5f5">
      <v-flex xs12 style="background-color: #ffffff;">
        <v-flex d-flex style="height: 128px;">
          <v-flex d-flex class="pl-12 ml-12 pr-12 mr-12 justify-center text-center align-center">
            <v-divider vertical v-if="selectedLangList.length >= 1"></v-divider>
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 1 && firstLang != '' && firstLang != null && firstLang != 'undefined'"
            >
              <v-col>
                <v-flex id="firstLang">
                  <v-autocomplete
                    v-model="firstLang"
                    :items="languageNameList"
                    :search-input="firstSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length >= 2"></v-divider>
            <v-flex
              v-if="selectedLangList.length >= 2 && secondLang != '' && secondLang != null && secondLang != 'undefined'"
            >
              <v-col>
                <v-flex id="secondLang">
                  <v-autocomplete
                    v-model="secondLang"
                    :items="languageNameList"
                    :search-input="secondSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length >= 3"></v-divider>
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 3 && thirdLang != '' && thirdLang != null && thirdLang != 'undefined'"
            >
              <v-col>
                <v-flex id="thirdLang">
                  <v-autocomplete
                    v-model="thirdLang"
                    :items="languageNameList"
                    :search-input="thirdSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length >= 4"></v-divider>
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 4 && fourthLang != '' && fourthLang != null && fourthLang != 'undefined'"
            >
              <v-col>
                <v-flex id="fourthLang">
                  <v-autocomplete
                    v-model="fourthLang"
                    :items="languageNameList"
                    :search-input="fourthSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length >= 5"></v-divider>
            <v-flex
              d-flex
              v-if="selectedLangList.length >= 5 && fifthLang != '' && fifthLang != null && fifthLang != 'undefined'"
            >
              <v-col>
                <v-flex id="fifthLang">
                  <v-autocomplete
                    v-model="fifthLang"
                    :items="languageNameList"
                    :search-input="fifthSearch"
                    hide-details
                    solo
                    flat
                    prepend-icon="mdi-checkbox-blank-circle"
                    color="white"
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical v-if="selectedLangList.length < 5"></v-divider>
            <v-flex d-flex v-if="selectedLangList.length < 5">
              <v-col>
                <v-flex>
                  <v-autocomplete
                    v-model="selectedLang"
                    :items="languageNameList"
                    :search-input.sync="search"
                    hide-details
                    placeholder="비교 추가"
                    prepend-icon="mdi-plus"
                    no-data-text="해당하는 언어가 없습니다"
                    open-on-clear
                    :append-icon="null"
                    color="white"
                    solo
                    flat
                  ></v-autocomplete>
                </v-flex>
              </v-col>
            </v-flex>
            <v-divider vertical></v-divider>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        justify-center
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 1"
        :key="brushComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">시간 흐름에 따른 관심도 변화</v-flex>
          <v-divider />
          <v-flex>
            <BrushChart
              :firstLang="firstLang"
              :firstValue="firstBrushValue"
              :secondLang="secondLang"
              :secondValue="secondBrushValue"
              :thirdLang="thirdLang"
              :thirdValue="thirdBrushValue"
              :fourthLang="fourthLang"
              :fourthValue="fourthBrushValue"
              :fifthLang="fifthLang"
              :fifthValue="fifthBrushValue"
              :colors="colors"
            ></BrushChart>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 2"
        :key="totalComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">지역별 비교 분석</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <TotalGeoChart
                :firstLang="firstLang"
                :secondLang="secondLang"
                :thirdLang="thirdLang"
                :fourthLang="fourthLang"
                :fifthLang="fifthLang"
                :sortValue="sortValue"
                :totalGeoValue="totalGeoValue"
                :selectedCnt="selectedLangList.length"
                :colors="colors"
              />
            </v-flex>
            <v-flex xs6 justify-end class="pl-3 ml-3">
              <v-flex>
                <v-flex d-flex>
                  <v-select v-model="sortValue" :items="selectedLangList" solo flat hide-details></v-select>
                </v-flex>
              </v-flex>
              <v-flex class="pr-3">
                <v-divider />
                <TotalGeoTable
                  :firstLang="firstLang"
                  :secondLang="secondLang"
                  :thirdLang="thirdLang"
                  :fourthLang="fourthLang"
                  :fifthLang="fifthLang"
                  :sortValue="sortValue"
                  :totalGeoValue="totalGeoValue"
                  :selectedCnt="selectedLangList.length"
                  :colors="colors"
                />
              </v-flex>
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 1"
        :key="firstComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[0] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="firstGeoValue" colors="#258df2" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="firstGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 2"
        :key="secondComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[1] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="secondGeoValue" colors="#ff4560" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="secondGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 3"
        :key="thirdComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[2] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="thirdGeoValue" colors="#fdba2c" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="thirdGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 4"
        :key="fourthComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[3] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="fourthGeoValue" colors="#40c741" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="fourthGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
      <v-flex
        d-flex
        xs10
        class="ml-auto mr-auto mt-7"
        style="background-color: #ffffff"
        v-if="selectedLangList.length >= 5"
        :key="fifthComponentKey"
      >
        <v-flex>
          <v-flex d-flex class="ma-3">{{ selectedLangList[4] }} 지역별 관심도</v-flex>
          <v-divider class="mb-3" />
          <v-flex d-flex>
            <v-flex d-flex xs6 justify-start class="pr-3">
              <GeoChart :value="fifthGeoValue" colors="#745af2" />
            </v-flex>
            <v-flex d-flex xs6 justify-end class="pl-3">
              <GeoTable :value="fifthGeoValue" />
            </v-flex>
          </v-flex>
        </v-flex>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import BrushChart from "../../components/BrushChart";
import TotalGeoChart from "../../components/TotalGeoChart";
import TotalGeoTable from "../../components/TotalGeoTable";
import GeoChart from "../../components/GeoChart";
import GeoTable from "../../components/GeoTable";
import baseURL from "@/base-url.js";

export default {
  name: "StatisticsPage",
  components: {
    BrushChart,
    TotalGeoChart,
    TotalGeoTable,
    GeoChart,
    GeoTable
  },
  computed: {},
  data() {
    return {
      // colors: ["#4285f4", "#f44336", "#ffca28", "#43a047", "#9c27b0"],
      colors: ["#258df2", "#ff4560", "#fdba2c", "#40c741", "#ab47bc"],
      selectedLangList: [],
      firstLang: "",
      secondLang: "",
      thirdLang: "",
      fourthLang: "",
      fifthLang: "",
      selectedLang: "",
      sortValue: "",
      firstSearch: null,
      secondSearch: null,
      thirdSearch: null,
      fourthSearch: null,
      fifthSearch: null,
      search: null,
      firstGeoValue: [],
      secondGeoValue: [],
      thirdGeoValue: [],
      fourthGeoValue: [],
      fifthGeoValue: [],
      firstBrushValue: [],
      secondBrushValue: [],
      thirdBrushValue: [],
      fourthBrushValue: [],
      fifthBrushValue: [],
      totalGeoValue: [["State", "관심도"]],
      brushComponentKey: 0,
      totalComponentKey: 200,
      firstComponentKey: 400,
      secondComponentKey: 600,
      thirdComponentKey: 800,
      fourthComponentKey: 1000,
      fifthComponentKey: 1200,
      languageNameList: [
        "JAVA",
        "C",
        "Python",
        "C++",
        "C#",
        "VB.NET",
        "JavaScript",
        "PHP",
        "SQL",
        "Go",
        "R",
        "Assembly",
        "Swift",
        "Ruby",
        "MATLAB",
        "PL/SQL",
        "Perl",
        "Visual Basic",
        "Objective-C",
        "Delphi"
      ],
      languages: [
        {
          languageId: 0,
          languageName: "JAVA"
        },
        {
          languageId: 1,
          languageName: "Python"
        },
        {
          languageId: 2,
          languageName: "C"
        },
        {
          languageId: 3,
          languageName: "C++"
        },
        {
          languageId: 4,
          languageName: "C#"
        },
        {
          languageId: 5,
          languageName: "JavaScript"
        }
      ],
      javaBrushValue: [],
      cBrushValue: [],
      pythonBrushValue: [],
      cppBrushValue: [],
      cSharpBrushValue: [],
      vbNetBrushValue: [],
      jsBrushValue: [],
      phpBrushValue: [],
      sqlBrushValue: [],
      goBrushValue: [],
      rBrushValue: [],
      assemblyBrushValue: [],
      swiftBrushValue: [],
      rubyBrushValue: [],
      matlabBrushValue: [],
      psBrushValue: [],
      perlBrushValue: [],
      vbBrushValue: [],
      obcBrushValue: [],
      delphiBrushValue: [],
      javaGeoValue: [["State", "관심도"]],
      cGeoValue: [["State", "관심도"]],
      pythonGeoValue: [["State", "관심도"]],
      cppGeoValue: [["State", "관심도"]],
      cSharpGeoValue: [["State", "관심도"]],
      vbNetGeoValue: [["State", "관심도"]],
      jsGeoValue: [["State", "관심도"]],
      phpGeoValue: [["State", "관심도"]],
      sqlGeoValue: [["State", "관심도"]],
      goGeoValue: [["State", "관심도"]],
      rGeoValue: [["State", "관심도"]],
      assemblyGeoValue: [["State", "관심도"]],
      swiftGeoValue: [["State", "관심도"]],
      rubyGeoValue: [["State", "관심도"]],
      matlabGeoValue: [["State", "관심도"]],
      psGeoValue: [["State", "관심도"]],
      perlGeoValue: [["State", "관심도"]],
      vbGeoValue: [["State", "관심도"]],
      obcGeoValue: [["State", "관심도"]],
      delphiGeoValue: [["State", "관심도"]]
    };
  },
  watch: {
    selectedLang(e) {
      if (e != "" && e != null && e != "undefined") {
        if (
          this.firstLang == "" ||
          this.firstLang == null ||
          this.firstLang == "undefined"
        ) {
          this.firstLang = e;
        } else if (
          this.secondLang == "" ||
          this.secondLang == null ||
          this.secondLang == "undefined"
        ) {
          this.secondLang = e;
        } else if (
          this.thirdLang == "" ||
          this.thirdLang == null ||
          this.thirdLang == "undefined"
        ) {
          this.thirdLang = e;
        } else if (
          this.fourthLang == "" ||
          this.fourthLang == null ||
          this.fourthLang == "undefined"
        ) {
          this.fourthLang = e;
        } else if (
          this.fifthLang == "" ||
          this.fifthLang == null ||
          this.fifthLang == "undefined"
        ) {
          this.fifthLang = e;
        }
      }
    },
    firstLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(0, 1);
        this.firstLang = this.secondLang;
        this.firstGeoValue =
          this.secondGeoValue == null || this.secondGeoValue == "undefined"
            ? []
            : this.secondGeoValue.slice(0, this.secondGeoValue);
        this.secondLang = this.thirdLang;
        this.secondGeoValue =
          this.thirdGeoValue == null || this.thirdGeoValue == "undefined"
            ? []
            : this.thirdGeoValue.slice(0, this.thirdGeoValue);
        this.thirdLang = this.fourthLang;
        this.thirdGeoValue =
          this.fourthGeoValue == null || this.fourthGeoValue == "undefined"
            ? []
            : this.fourthGeoValue.slice(0, this.fourthGeoValue);
        this.fourthLang = "";
        this.fourthGeoValue = [];
        window.sessionStorage.removeItem("firstLang");
        window.sessionStorage.removeItem("firstGeoValue");
        window.sessionStorage.removeItem("firstBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLang = null;
        this.selectedLang = "";
        this.search = null;
        this.selectedLangList.splice(0, 1, e);
        this.sortValue = e;

        if (e == "JAVA") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.javaGeoValue;
          this.firstBrushValue = [];
          this.firstBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "C") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "Python") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "VB.NET") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.vbNetGeoValue.slice(
            0,
            this.vbNetGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.vbNetBrushValue.slice(
            0,
            this.vbNetBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.jsGeoValue.slice(0, this.jsGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        } else if (e == "PHP") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.phpGeoValue.slice(
            0,
            this.phpGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.phpBrushValue.slice(
            0,
            this.phpBrushValue.length
          );
        } else if (e == "SQL") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.sqlGeoValue.slice(
            0,
            this.sqlGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.sqlBrushValue.slice(
            0,
            this.sqlBrushValue.length
          );
        } else if (e == "Go") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.goGeoValue.slice(0, this.goGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.goBrushValue.slice(
            0,
            this.goBrushValue.length
          );
        } else if (e == "R") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.rGeoValue.slice(0, this.rGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.rBrushValue.slice(
            0,
            this.rBrushValue.length
          );
        } else if (e == "Assembly") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.assemblyGeoValue.slice(
            0,
            this.assemblyGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.assemblyBrushValue.slice(
            0,
            this.assemblyBrushValue.length
          );
        } else if (e == "Swift") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.swiftGeoValue.slice(
            0,
            this.swiftGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.swiftBrushValue.slice(
            0,
            this.swiftBrushValue.length
          );
        } else if (e == "Ruby") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.rubyGeoValue.slice(
            0,
            this.rubyGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.rubyBrushValue.slice(
            0,
            this.rubyBrushValue.length
          );
        } else if (e == "MATLAB") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.matlabGeoValue.slice(
            0,
            this.matlabGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.matlabBrushValue.slice(
            0,
            this.matlabBrushValue.length
          );
        } else if (e == "PL/SQL") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.psGeoValue.slice(0, this.psGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.psBrushValue.slice(
            0,
            this.psBrushValue.length
          );
        } else if (e == "Perl") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.perlGeoValue.slice(
            0,
            this.perlGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.perlBrushValue.slice(
            0,
            this.perlBrushValue.length
          );
        } else if (e == "Visual Basic") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.vbGeoValue.slice(0, this.vbGeoValue.length);
          this.firstBrushValue = [];
          this.firstBrushValue = this.vbBrushValue.slice(
            0,
            this.vbBrushValue.length
          );
        } else if (e == "Objective-C") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.obcGeoValue.slice(
            0,
            this.obcGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.obcBrushValue.slice(
            0,
            this.obcBrushValue.length
          );
        } else if (e == "Delphi") {
          this.firstGeoValue = [];
          this.firstGeoValue = this.delphiGeoValue.slice(
            0,
            this.delphiGeoValue.length
          );
          this.firstBrushValue = [];
          this.firstBrushValue = this.delphiBrushValue.slice(
            0,
            this.delphiBrushValue.length
          );
        }
        window.sessionStorage.setItem("firstLang", e);
        window.sessionStorage.setItem(
          "firstGeoValue",
          JSON.stringify(this.firstGeoValue)
        );
        window.sessionStorage.setItem("firstBrushValue", this.firstBrushValue);
      }
      this.forceRerender();
    },
    secondLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(1, 1);
        this.secondLang = this.thirdLang;
        this.secondGeoValue =
          this.thirdGeoValue == null || this.thirdGeoValue == "undefined"
            ? []
            : this.thirdGeoValue.slice(0, this.thirdGeoValue);
        this.thirdLang = this.fourthLang;
        this.thirdGeoValue =
          this.fourthGeoValue == null || this.fourthGeoValue == "undefined"
            ? []
            : this.fourthGeoValue.slice(0, this.fourthGeoValue);
        this.fourthLang = "";
        this.fourthGeoValue = [];
        window.sessionStorage.removeItem("secondLang");
        window.sessionStorage.removeItem("secondGeoValue");
        window.sessionStorage.removeItem("secondBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLang = null;
        this.selectedLang = "";
        this.search = null;
        this.selectedLangList.splice(1, 1, e);

        if (e == "JAVA") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.javaGeoValue;
          this.secondBrushValue = [];
          this.secondBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "C") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.secondBrushValue = [];
          this.secondBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "Python") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "VB.NET") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.vbNetGeoValue.slice(
            0,
            this.vbNetGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.vbNetBrushValue.slice(
            0,
            this.vbNetBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.jsGeoValue.slice(
            0,
            this.jsGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        } else if (e == "PHP") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.phpGeoValue.slice(
            0,
            this.phpGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.phpBrushValue.slice(
            0,
            this.phpBrushValue.length
          );
        } else if (e == "SQL") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.sqlGeoValue.slice(
            0,
            this.sqlGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.sqlBrushValue.slice(
            0,
            this.sqlBrushValue.length
          );
        } else if (e == "Go") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.goGeoValue.slice(
            0,
            this.goGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.goBrushValue.slice(
            0,
            this.goBrushValue.length
          );
        } else if (e == "R") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.rGeoValue.slice(0, this.rGeoValue.length);
          this.secondBrushValue = [];
          this.secondBrushValue = this.rBrushValue.slice(
            0,
            this.rBrushValue.length
          );
        } else if (e == "Assembly") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.assemblyGeoValue.slice(
            0,
            this.assemblyGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.assemblyBrushValue.slice(
            0,
            this.assemblyBrushValue.length
          );
        } else if (e == "Swift") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.swiftGeoValue.slice(
            0,
            this.swiftGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.swiftBrushValue.slice(
            0,
            this.swiftBrushValue.length
          );
        } else if (e == "Ruby") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.rubyGeoValue.slice(
            0,
            this.rubyGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.rubyBrushValue.slice(
            0,
            this.rubyBrushValue.length
          );
        } else if (e == "MATLAB") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.matlabGeoValue.slice(
            0,
            this.matlabGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.matlabBrushValue.slice(
            0,
            this.matlabBrushValue.length
          );
        } else if (e == "PL/SQL") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.psGeoValue.slice(
            0,
            this.psGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.psBrushValue.slice(
            0,
            this.psBrushValue.length
          );
        } else if (e == "Perl") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.perlGeoValue.slice(
            0,
            this.perlGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.perlBrushValue.slice(
            0,
            this.perlBrushValue.length
          );
        } else if (e == "Visual Basic") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.vbGeoValue.slice(
            0,
            this.vbGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.vbBrushValue.slice(
            0,
            this.vbBrushValue.length
          );
        } else if (e == "Objective-C") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.obcGeoValue.slice(
            0,
            this.obcGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.obcBrushValue.slice(
            0,
            this.obcBrushValue.length
          );
        } else if (e == "Delphi") {
          this.secondGeoValue = [];
          this.secondGeoValue = this.delphiGeoValue.slice(
            0,
            this.delphiGeoValue.length
          );
          this.secondBrushValue = [];
          this.secondBrushValue = this.delphiBrushValue.slice(
            0,
            this.delphiBrushValue.length
          );
        }
        window.sessionStorage.setItem("secondLang", e);
        window.sessionStorage.setItem("secondGeoValue", this.secondGeoValue);
        window.sessionStorage.setItem(
          "secondBrushValue",
          this.secondBrushValue
        );
      }
      this.forceRerender();
    },
    thirdLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(2, 1);
        this.thirdLang = this.fourthLang;
        this.thirdGeoValue =
          this.fourthGeoValue == null || this.fourthGeoValue == "undefined"
            ? []
            : this.fourthGeoValue.slice(0, this.fourthGeoValue);
        this.fourthLang = this.fifthLang;
        this.fourthGeoValue =
          this.fifthGeoValue == null || this.fifthGeoValue == "undefined"
            ? []
            : this.fifthGeoValue.slice(0, this.fifthGeoValue);
        this.fifthLang = "";
        this.fifthGeoValue = [];
        window.sessionStorage.removeItem("thirdLang");
        window.sessionStorage.removeItem("thirdGeoValue");
        window.sessionStorage.removeItem("thirdBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLang = null;
        this.selectedLang = "";
        this.search = null;
        this.selectedLangList.splice(2, 1, e);

        if (e == "JAVA") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.javaGeoValue;
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "C") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "Python") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "VB.NET") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.vbNetGeoValue.slice(
            0,
            this.vbNetGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.vbNetBrushValue.slice(
            0,
            this.vbNetBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.jsGeoValue.slice(0, this.jsGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        } else if (e == "PHP") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.phpGeoValue.slice(
            0,
            this.phpGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.phpBrushValue.slice(
            0,
            this.phpBrushValue.length
          );
        } else if (e == "SQL") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.sqlGeoValue.slice(
            0,
            this.sqlGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.sqlBrushValue.slice(
            0,
            this.sqlBrushValue.length
          );
        } else if (e == "Go") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.goGeoValue.slice(0, this.goGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.goBrushValue.slice(
            0,
            this.goBrushValue.length
          );
        } else if (e == "R") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.rGeoValue.slice(0, this.rGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.rBrushValue.slice(
            0,
            this.rBrushValue.length
          );
        } else if (e == "Assembly") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.assemblyGeoValue.slice(
            0,
            this.assemblyGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.assemblyBrushValue.slice(
            0,
            this.assemblyBrushValue.length
          );
        } else if (e == "Swift") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.swiftGeoValue.slice(
            0,
            this.swiftGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.swiftBrushValue.slice(
            0,
            this.swiftBrushValue.length
          );
        } else if (e == "Ruby") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.rubyGeoValue.slice(
            0,
            this.rubyGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.rubyBrushValue.slice(
            0,
            this.rubyBrushValue.length
          );
        } else if (e == "MATLAB") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.matlabGeoValue.slice(
            0,
            this.matlabGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.matlabBrushValue.slice(
            0,
            this.matlabBrushValue.length
          );
        } else if (e == "PL/SQL") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.psGeoValue.slice(0, this.psGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.psBrushValue.slice(
            0,
            this.psBrushValue.length
          );
        } else if (e == "Perl") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.perlGeoValue.slice(
            0,
            this.perlGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.perlBrushValue.slice(
            0,
            this.perlBrushValue.length
          );
        } else if (e == "Visual Basic") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.vbGeoValue.slice(0, this.vbGeoValue.length);
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.vbBrushValue.slice(
            0,
            this.vbBrushValue.length
          );
        } else if (e == "Objective-C") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.obcGeoValue.slice(
            0,
            this.obcGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.obcBrushValue.slice(
            0,
            this.obcBrushValue.length
          );
        } else if (e == "Delphi") {
          this.thirdGeoValue = [];
          this.thirdGeoValue = this.delphiGeoValue.slice(
            0,
            this.delphiGeoValue.length
          );
          this.thirdBrushValue = [];
          this.thirdBrushValue = this.delphiBrushValue.slice(
            0,
            this.delphiBrushValue.length
          );
        }
        window.sessionStorage.setItem("thirdLang", e);
        window.sessionStorage.setItem("thirdGeoValue", this.thirdGeoValue);
        window.sessionStorage.setItem("thirdBrushValue", this.thirdBrushValue);
      }
      this.forceRerender();
    },
    fourthLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(3, 1);
        this.fourthLang = this.fifthLang;
        this.fourthGeoValue =
          this.fifthGeoValue == null || this.fifthGeoValue == "undefined"
            ? []
            : this.fifthGeoValue.slice(0, this.fifthGeoValue);
        this.fifthLang = "";
        window.sessionStorage.removeItem("fourthLang");
        window.sessionStorage.removeItem("fourthGeoValue");
        window.sessionStorage.removeItem("fourthBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLangList.splice(3, 1, e);
        this.selectedLang = null;
        this.search = null;

        if (e == "JAVA") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.javaGeoValue;
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "C") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "Python") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "VB.NET") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.vbNetGeoValue.slice(
            0,
            this.vbNetGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.vbNetBrushValue.slice(
            0,
            this.vbNetBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.jsGeoValue.slice(
            0,
            this.jsGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        } else if (e == "PHP") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.phpGeoValue.slice(
            0,
            this.phpGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.phpBrushValue.slice(
            0,
            this.phpBrushValue.length
          );
        } else if (e == "SQL") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.sqlGeoValue.slice(
            0,
            this.sqlGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.sqlBrushValue.slice(
            0,
            this.sqlBrushValue.length
          );
        } else if (e == "Go") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.goGeoValue.slice(
            0,
            this.goGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.goBrushValue.slice(
            0,
            this.goBrushValue.length
          );
        } else if (e == "R") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.rGeoValue.slice(0, this.rGeoValue.length);
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.rBrushValue.slice(
            0,
            this.rBrushValue.length
          );
        } else if (e == "Assembly") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.assemblyGeoValue.slice(
            0,
            this.assemblyGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.assemblyBrushValue.slice(
            0,
            this.assemblyBrushValue.length
          );
        } else if (e == "Swift") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.swiftGeoValue.slice(
            0,
            this.swiftGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.swiftBrushValue.slice(
            0,
            this.swiftBrushValue.length
          );
        } else if (e == "Ruby") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.rubyGeoValue.slice(
            0,
            this.rubyGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.rubyBrushValue.slice(
            0,
            this.rubyBrushValue.length
          );
        } else if (e == "MATLAB") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.matlabGeoValue.slice(
            0,
            this.matlabGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.matlabBrushValue.slice(
            0,
            this.matlabBrushValue.length
          );
        } else if (e == "PL/SQL") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.psGeoValue.slice(
            0,
            this.psGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.psBrushValue.slice(
            0,
            this.psBrushValue.length
          );
        } else if (e == "Perl") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.perlGeoValue.slice(
            0,
            this.perlGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.perlBrushValue.slice(
            0,
            this.perlBrushValue.length
          );
        } else if (e == "Visual Basic") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.vbGeoValue.slice(
            0,
            this.vbGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.vbBrushValue.slice(
            0,
            this.vbBrushValue.length
          );
        } else if (e == "Objective-C") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.obcGeoValue.slice(
            0,
            this.obcGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.obcBrushValue.slice(
            0,
            this.obcBrushValue.length
          );
        } else if (e == "Delphi") {
          this.fourthGeoValue = [];
          this.fourthGeoValue = this.delphiGeoValue.slice(
            0,
            this.delphiGeoValue.length
          );
          this.fourthBrushValue = [];
          this.fourthBrushValue = this.delphiBrushValue.slice(
            0,
            this.delphiBrushValue.length
          );
        }
        window.sessionStorage.setItem("fourthLang", e);
        window.sessionStorage.setItem("fourthGeoValue", this.fourthGeoValue);
        window.sessionStorage.setItem(
          "fourthBrushValue",
          this.fourthBrushValue
        );
      }
      this.forceRerender();
    },
    fifthLang(e) {
      if (e == "" || e == null || e == "undefined") {
        this.selectedLangList.splice(4, 1);
        this.fifthGeoValue = [];
        this.fifthLang = "";
        window.sessionStorage.removeItem("fifthLang");
        window.sessionStorage.removeItem("fifthGeoValue");
        window.sessionStorage.removeItem("fifthBrushValue");
      } else if (e != "" && e != null && e != "undefined") {
        this.selectedLangList.splice(4, 1, e);
        this.selectedLang = null;
        this.search = null;

        if (e == "JAVA") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.javaGeoValue;
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.javaBrushValue.slice(
            0,
            this.javaBrushValue.length
          );
        } else if (e == "C") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.cGeoValue.slice(0, this.cGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.cBrushValue.slice(
            0,
            this.cBrushValue.length
          );
        } else if (e == "Python") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.pythonGeoValue.slice(
            0,
            this.pythonGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.pythonBrushValue.slice(
            0,
            this.pythonBrushValue.length
          );
        } else if (e == "C++") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.cppGeoValue.slice(
            0,
            this.cppGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.cppBrushValue.slice(
            0,
            this.cppBrushValue.length
          );
        } else if (e == "C#") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.cSharpGeoValue.slice(
            0,
            this.cSharpGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.cSharpBrushValue.slice(
            0,
            this.cSharpBrushValue.length
          );
        } else if (e == "VB.NET") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.vbNetGeoValue.slice(
            0,
            this.vbNetGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.vbNetBrushValue.slice(
            0,
            this.vbNetBrushValue.length
          );
        } else if (e == "JavaScript") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.jsGeoValue.slice(0, this.jsGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.jsBrushValue.slice(
            0,
            this.jsBrushValue.length
          );
        } else if (e == "PHP") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.phpGeoValue.slice(
            0,
            this.phpGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.phpBrushValue.slice(
            0,
            this.phpBrushValue.length
          );
        } else if (e == "SQL") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.sqlGeoValue.slice(
            0,
            this.sqlGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.sqlBrushValue.slice(
            0,
            this.sqlBrushValue.length
          );
        } else if (e == "Go") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.goGeoValue.slice(0, this.goGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.goBrushValue.slice(
            0,
            this.goBrushValue.length
          );
        } else if (e == "R") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.rGeoValue.slice(0, this.rGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.rBrushValue.slice(
            0,
            this.rBrushValue.length
          );
        } else if (e == "Assembly") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.assemblyGeoValue.slice(
            0,
            this.assemblyGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.assemblyBrushValue.slice(
            0,
            this.assemblyBrushValue.length
          );
        } else if (e == "Swift") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.swiftGeoValue.slice(
            0,
            this.swiftGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.swiftBrushValue.slice(
            0,
            this.swiftBrushValue.length
          );
        } else if (e == "Ruby") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.rubyGeoValue.slice(
            0,
            this.rubyGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.rubyBrushValue.slice(
            0,
            this.rubyBrushValue.length
          );
        } else if (e == "MATLAB") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.matlabGeoValue.slice(
            0,
            this.matlabGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.matlabBrushValue.slice(
            0,
            this.matlabBrushValue.length
          );
        } else if (e == "PL/SQL") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.psGeoValue.slice(0, this.psGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.psBrushValue.slice(
            0,
            this.psBrushValue.length
          );
        } else if (e == "Perl") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.perlGeoValue.slice(
            0,
            this.perlGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.perlBrushValue.slice(
            0,
            this.perlBrushValue.length
          );
        } else if (e == "Visual Basic") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.vbGeoValue.slice(0, this.vbGeoValue.length);
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.vbBrushValue.slice(
            0,
            this.vbBrushValue.length
          );
        } else if (e == "Objective-C") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.obcGeoValue.slice(
            0,
            this.obcGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.obcBrushValue.slice(
            0,
            this.obcBrushValue.length
          );
        } else if (e == "Delphi") {
          this.fifthGeoValue = [];
          this.fifthGeoValue = this.delphiGeoValue.slice(
            0,
            this.delphiGeoValue.length
          );
          this.fifthBrushValue = [];
          this.fifthBrushValue = this.delphiBrushValue.slice(
            0,
            this.delphiBrushValue.length
          );
        }
        window.sessionStorage.setItem("fifthLang", e);
        window.sessionStorage.setItem("fifthGeoValue", this.fifthGeoValue);
        window.sessionStorage.setItem("fifthBrushValue", this.fifthBrushValue);
      }
      this.forceRerender();
    },
    totalComponentKey() {
      this.totalGeoValue = [];
      var tmpValue = [];

      if (this.firstGeoValue != null && this.firstGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = JSON.parse(JSON.stringify(this.firstGeoValue));
        this.totalGeoValue = tmpValue.slice(1, tmpValue.length).sort();
      }

      if (this.secondGeoValue != null && this.secondGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.secondGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      if (this.thirdGeoValue != null && this.thirdGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.thirdGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      if (this.fourthGeoValue != null && this.fourthGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.fourthGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      if (this.fifthGeoValue != null && this.fifthGeoValue != "undefined") {
        tmpValue = [];
        tmpValue = this.fifthGeoValue;
        tmpValue = tmpValue.slice(1, tmpValue.length).sort();

        for (let i = 0; i < this.totalGeoValue.length; i++) {
          for (let j = 0; j < tmpValue.length; j++) {
            if (this.totalGeoValue[i][0] == tmpValue[j][0]) {
              this.totalGeoValue[i].push(tmpValue[j][1]);
            }
          }
        }
      }

      for (let i = 0; i < this.totalGeoValue.length; i++) {
        if (this.totalGeoValue[i][0] == "서울") {
          this.totalGeoValue[i][0] = "서울특별시";
        } else if (this.totalGeoValue[i][0] == "제주도") {
          this.totalGeoValue[i][0] = "제주특별자치도";
        }
      }
    },
    sortValue() {
      this.forceRerender();
    }
  },
  methods: {
    forceRerender() {
      this.brushComponentKey += 1;
      this.totalComponentKey += 1;
      this.firstComponentKey += 1;
      this.secondComponentKey += 1;
      this.thirdComponentKey += 1;
      this.fourthComponentKey += 1;
      this.fifthComponentKey += 1;
    },
    clone(obj) {
      var output = [];

      for (let i = 0; i < obj.length; i++) {
        output[i] = obj[i];
      }
      return output;
    },
    getDateData() {
      baseURL
        .get("/google/findAll/date")
        .then(res => {
          var tmpMap = res.data["Java"];
          var tmpKeys = Object.keys(tmpMap);
          var tmpValues = Object.values(tmpMap);
          var tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.javaBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["C"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.cBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Python"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.pythonBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["C++"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.cppBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["C#"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.cSharpBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["VB.NET"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.vbNetBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["JavaScript"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.jsBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["PHP"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.phpBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["SQL"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.sqlBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Go"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.goBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["R"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.rBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Assembly"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.assemblyBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Swift"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.swiftBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Ruby"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.rubyBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["MATLAB"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.matlabBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["PL/SQL"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.psBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Perl"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.perlBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Visual Basic"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.vbBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Objective-C"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.obcBrushValue = tmpList.sort().slice(0, tmpList.length);

          tmpMap = res.data["Delphi"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);
          tmpList = [];

          for (let i = 0; i < tmpKeys.length; i++) {
            tmpList.push([tmpKeys[i], tmpValues[i]]);
          }
          this.delphiBrushValue = tmpList.sort().slice(0, tmpList.length);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getGeoData() {
      baseURL
        .get("/google/findAll/geo")
        .then(res => {
          var tmpMap = res.data["Java"];
          var tmpKeys = Object.keys(tmpMap);
          var tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.javaGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.javaGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["C"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.cGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.cGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Python"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.pythonGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.pythonGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["C++"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.cppGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.cppGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["C#"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.cSharpGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.cSharpGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["VB.NET"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.vbNetGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.vbNetGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["JavaScript"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.jsGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.jsGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["PHP"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.phpGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.phpGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["SQL"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.sqlGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.sqlGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Go"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.goGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.goGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["R"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.rGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.rGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Assembly"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.assemblyGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.assemblyGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Swift"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.swiftGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.swiftGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Ruby"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.rubyGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.rubyGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["MATLAB"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.matlabGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.matlabGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["PL/SQL"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.psGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.psGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Perl"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.perlGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.perlGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Visual Basic"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.vbGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.vbGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Objective-C"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.obcGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.obcGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });

          tmpMap = res.data["Delphi"];
          tmpKeys = Object.keys(tmpMap);
          tmpValues = Object.values(tmpMap);

          for (let i = 0; i < tmpKeys.length; i++) {
            this.delphiGeoValue.push([tmpKeys[i], tmpValues[i]]);
          }
          this.delphiGeoValue.sort(function(a, b) {
            return a[1] > b[1] ? -1 : a[1] < b[1] ? 1 : 0;
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    init() {
      this.getDateData();
      this.getGeoData();
    }
  },
  created() {
    this.init();
    this.brushComponentKey = 0;
    this.totalComponentKey = 200;
    this.firstComponentKey = 400;
    this.secondComponentKey = 600;
    this.thirdComponentKey = 800;
    this.fourthComponentKey = 1000;
    this.fifthComponentKey = 1200;
    this.sortValue = this.selectedLangList[0];
  }
};
</script>
<style>
div#firstLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #258df2;
}

div#secondLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #ff4560;
}

div#thirdLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #fdba2c;
}

div#fourthLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #40c741;
}

div#fifthLang
  > div.v-input.v-input--hide-details.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--single-line.v-text-field--solo.v-text-field--solo-flat.v-text-field--is-booted.v-text-field--enclosed.v-select.v-autocomplete
  > div.v-input__prepend-outer
  > div.v-input__icon.v-input__icon--prepend
  > i.v-icon.notranslate.mdi.mdi-checkbox-blank-circle.theme--light {
  color: #ab47bc;
}
</style>