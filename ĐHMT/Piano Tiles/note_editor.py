import json

twinkle_twinkle = ['M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg',\
                'M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg',\
                'M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg',\
                'M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg',\
                'M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg',\
                'M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg','M.ogg',\
                ]



notes = {
    '1' : twinkle_twinkle,

}

with open('notes.json', 'w') as file:
    json.dump(notes, file)