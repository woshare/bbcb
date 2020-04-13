package com.example.bbcb.dto;

import java.util.List;

public class WordStruct {
    private String word;//单词原型

    private List<String> phonetics;//音标

    private List<String> transForms;//变形，时态，单复数

    private List<String> wordMeansAndExamples;//词性，释义（中英），例句，近反义词，示意图

    private List<String> wordUsage;//单词用法

    private List<String> derivedWords;//派生词

    private List<String> phraseWords;//短语

    private List<String> mnemonics;//助记

    private List<String> levels;//级别，四六级，雅思托福

    private List<String> affix;//词根词缀：prefix 前缀， suffix 后缀,cognate 同根词

    private Float wordFrequency;//词频

    private Float score;//单词星级评分

    private String dataOrigin;//词源


}
