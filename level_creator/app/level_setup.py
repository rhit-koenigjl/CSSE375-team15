import inquirer
import os
import json

class LevelSetup():
    def __init__(self, directory_path) -> None:
        new_or_edit = [
            inquirer.List('new_or_edit',
                        message="Would you like to create a new level, or edit an existing one",
                        choices=['new', 'edit']
                    )
        ]

        new_level = [
            inquirer.Text('name', message="Level name"),
            inquirer.Text('width', message="Level width"),
            inquirer.Text('height', message="Level height"),
        ]

        which_level = [
            inquirer.List('level',
                        message="Which level do you want to update",
                        choices=os.listdir(directory_path),
                    )
        ]

        answer = inquirer.prompt(new_or_edit)
        if answer['new_or_edit'] == 'new':
            l_data = inquirer.prompt(new_level)
            self.file_path = directory_path + '\\' + l_data['name'] + '.json'
            l_object = {
                'width': l_data['width'],
                'height': l_data['height'],
                'data': ''
            }
            json.dump(l_object, open(self.file_path, 'w'))
            return

        answer = inquirer.prompt(which_level)
        self.file_path = directory_path + '\\' + answer['level']

    def get_level_object(self):
        f = open(self.file_path)
        return json.load(f)
    
    def dump_data(self, stream):
        fd = open(self.file_path, 'r+')
        d = json.load(fd)
        fd.seek(0)
        d['data'] = stream
        json.dump(d, fd)