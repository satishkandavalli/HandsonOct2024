package com.test
import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText(
        '''{
    "workspaces": [
        {
            "id": "0a6deece-ccec-4b81-888c-ecdc36cf7825",
            "name": "APITestingFramework",
            "type": "personal",
            "visibility": "personal",
            "createdBy": "20979684"
        },
        {
            "id": "f3585367-07a8-46f1-9e32-81e897fadf1b",
            "name": "TestWorkspaceModified",
            "type": "personal",
            "visibility": "personal",
            "createdBy": "20979684"
        },
        {
            "id": "016010e9-3aa2-4b09-86ac-0244fec26efd",
            "name": "TestWorkspace2",
            "type": "personal",
            "visibility": "personal",
            "createdBy": "20979684"
        },
        {
            "id": "ba0ecacc-7d1a-4025-a94f-a97d49cf0642",
            "name": "TestWorkspace3",
            "type": "personal",
            "visibility": "personal",
            "createdBy": "20979684"
        }
    ]
}
'''
)

println(object.workspaces[0].visibility)
