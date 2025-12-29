package com.example.bottomnavigationapplication.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun main() {
//    把json转换为对象
    val json = "{\"keepAlive\":0," +
            " \"uid\":123," +
            " \"hidden\":0," +
            " \"userName\":\"system\"," +
            " \"title\":\"游戏管理\"}"
    val gson = Gson()
    val account: Account2 = gson.fromJson<Account2>(json, Account2::class.java)
    println("fromJSON:${account.toString()}")

//    把对象转换成json
    val accountJson = gson.toJson(account)
    println("toJson:${accountJson}")

//    将json转换成集合
    val jsonList = "[\n" +
            "                                        {\n" +
            "                                            \"keepAlive\":0,\n" +
            "                                            \"hidden\":0,\n" +
            "                                            \"title\":\"投注参数-修改\",\n" +
            "                                            \"type\":2,\n" +
            "                                            \"parentId\":24\n" +
            "                                        },\n" +
            "                                        {\n" +
            "                                            \"keepAlive\":0,\n" +
            "                                            \"hidden\":0,\n" +
            "                                            \"title\":\"资金分配-修改\",\n" +
            "                                            \"type\":2,\n" +
            "                                            \"parentId\":24\n" +
            "                                        },\n" +
            "                                        {\n" +
            "                                            \"keepAlive\":0,\n" +
            "                                            \"hidden\":0,\n" +
            "                                            \"title\":\"新期参数-修改\",\n" +
            "                                            \"type\":2,\n" +
            "                                            \"parentId\":24\n" +
            "                                        },\n" +
            "                                        {\n" +
            "                                            \"keepAlive\":0,\n" +
            "                                            \"hidden\":0,\n" +
            "                                            \"title\":\"游戏规则修改记录\",\n" +
            "                                            \"type\":2,\n" +
            "                                            \"parentId\":24\n" +
            "                                        }\n" +
            "                                    ]"
    val accountList: List<Account> =
        gson.fromJson(jsonList, object : TypeToken<List<Account>>() {}.type)
    println("fromJson to List:${accountList.size}")

//    将集合转成json
    val accountJsonList = gson.toJson(accountList)
    println("list to json:${accountJsonList}")

    val petModelJson = "{\n" +
            "    \"code\": 0,\n" +
            "    \"data\": [\n" +
            "        {\n" +
            "            \"name\": \"Hello Kity\",\n" +
            "            \"photoUrls\": [\n" +
            "                \"http://dummyimage.com/400x400\"\n" +
            "            ],\n" +
            "            \"id\": 3,\n" +
            "            \"category\": {\n" +
            "                \"id\": 71,\n" +
            "                \"name\": \"Cat\"\n" +
            "            },\n" +
            "            \"tags\": [\n" +
            "                {\n" +
            "                    \"id\": 22,\n" +
            "                    \"name\": \"Cat\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"status\": \"sold\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"White Dog\",\n" +
            "            \"photoUrls\": [\n" +
            "                \"http://dummyimage.com/400x400\"\n" +
            "            ],\n" +
            "            \"id\": 3,\n" +
            "            \"category\": {\n" +
            "                \"id\": 71,\n" +
            "                \"name\": \"Dog\"\n" +
            "            },\n" +
            "            \"tags\": [\n" +
            "                {\n" +
            "                    \"id\": 22,\n" +
            "                    \"name\": \"Dog\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"status\": \"sold\"\n" +
            "        }\n" +
            "    ]\n" +
            "}"
    val petResponse : PetResponse = gson.fromJson<PetResponse>(petModelJson, PetResponse::class.java)
    println("petResponse:$petResponse")
}

data class Account2 (
    val uid: String = "456",
    val userName: String = "",
    val password: String = "",
    val telephone: String = ""
)

class Account {
    val uid: String = ""
    val userName: String = "lebron"
    val password: String = "123456"
    val telephone: String = "13111112222"
    override fun toString(): String {
        return "Account(uid='$uid', userName='$userName', password='$password', telephone='$telephone')"
    }
}

data class PetResponse(
    val code: Int,
    val data: List<Data>
)

data class Data(
    val category: Category,
    val id: Int,
    val name: String,
    val photoUrls: List<String>,
    val status: String,
    val tags: List<Tag>
)

data class Category(
    val id: Int,
    val name: String
)

data class Tag(
    val id: Int,
    val name: String
)