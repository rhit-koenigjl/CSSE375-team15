import pygame

class BlockPallete:
    def __init__(self, asset_list) -> None:
        self.current_selections = []
        self.collections = []
        self.current_collection = 2
        
        for collection in asset_list:
            self.current_selections.append(0)
            keys = []
            for asset in list(collection):
                keys.append(asset[0])
            self.collections.append(keys)

        self.selected_img = pygame.image.load("assets\\selected_square.png")

    def get_active_type(self):
        return self.collections[self.current_collection][self.current_selections[self.current_collection]]
    
    def iterate_collection(self):
        self.current_selections[self.current_collection] += 1
        print(len(self.collections[self.current_collection]))
        self.current_selections[self.current_collection] %= len(self.collections[self.current_collection])

    def set_collection(self, collection):
        if (collection > len(self.collections) - 1):
            return
        self.current_collection = collection

    def display(self, screen: pygame.surface, image_map):
        size = 40
        for i, c in enumerate(self.collections):
            screen.blit(pygame.transform.scale(image_map[c[self.current_selections[i]]], (size, size)), (screen.get_size()[0] - 40, size * i))
            if i == self.current_collection:
                screen.blit(pygame.transform.scale(self.selected_img, (size, size)), (screen.get_size()[0] - 40, size * i))

